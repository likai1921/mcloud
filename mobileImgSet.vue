 <template>
  <div class="app-container">
    <ul class="header">
      <li>
        模型：
        <el-select v-model="mod" size="mini" filterable clearable placeholder="请选择模型" @change="search">
          <el-option v-for="item in modlist" :key="item.id" :label="item.modelname" :value="item.id" />
        </el-select>
      </li>
      <li>
        图片标签：
        <el-cascader :key="keyvalue + 'tag'" v-model="sdTagIds" size="mini" expand-trigger="hover"
          :show-all-levels="false" placeholder="请选择图片标签" :options="sdTagIdlist"
          :props="{ multiple: true, value: 'id', label: 'name' }" clearable @change="search" />
      </li>
      <li>
        手机屏保：
        <el-radio-group v-model="bj" @change="search">
          <el-radio label="">全部</el-radio>
          <el-radio :label="0">未标记</el-radio>
          <el-radio :label="1">已标记</el-radio>
        </el-radio-group>
      </li>
      <li>
        <el-button size="mini" type="primary" @click="search">搜索</el-button>
      </li>
      <!-- <li style="margin-left: auto">
        <el-button
          size="mini"
          :disabled="selectlist.length <= 0"
          type="warning"
          @click="handlelinggan"
        >批量标记灵感</el-button>
      </li> -->
    </ul>
    <main>
      <el-table border :data="tableData" height="75vh" @selection-change="handleSelectionChange">
        <!-- <el-table-column type="selection" width="55" />
        <el-table-column align="center" label="顺序" width="55" type="index" /> -->
        <el-table-column align="center" prop="id" label="id" />
        <el-table-column align="center" label="图片" min-width="150">
          <template slot-scope="scope">
            <div style="display: flex; justify-content: center">
              <div @click="previewShow(scope)">
                <img class="mr20 cp" style="max-width: 100px; max-height: 100px" :src="scope.row.url" alt="">
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="name" label="标签" />
        <el-table-column align="center" prop="modelName" label="模型版本" />
        <el-table-column align="center" prop="prompt" label="提示词" min-width="200" show-overflow-tooltip />
        <el-table-column align="center" prop="negativePrompt" label="反向提示词" min-width="200" show-overflow-tooltip />
        <el-table-column align="center" prop="urlStatus" label="屏保标记">
          <template slot-scope="{ row }">
            <span>{{ row.osStatus === 1 ? "已" : "未" }}标记</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" type="danger" @click="deltype(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="footer fr mt20">
        <el-pagination :page-sizes="[20, 50, 100, 150, 200]" :page-size="form.pageSize" :current-page="pageNum"
          layout="total, sizes, prev, pager, next, jumper" :total="total" prev-text="上一页" next-text="下一页" background
          @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </main>
    <ImgPreview v-if="preivewdialog" ref="ImgPreview" :list="tableData" :currentnum="currentnum"
      @previewClose="previewClose" @currentnumChange="currentnumChange">
      <template slot="content">
        <li>
          <template>
            <el-radio v-model="is18type" :label="1">国内可用</el-radio>
            <el-radio v-model="is18type" :label="2">18+</el-radio>
          </template>
        </li>
        <li>
        </li>
        <li>
          <template>
            分类标签：
            <el-select size="mini" v-model="tagId" clearable filterable placeholder="请选择">
              <el-option v-for="item in tagIdList" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </template>
        </li>
        <li> </li>
        <li>
          <el-button size="mini" :type="issigntxt === 1 ? 'info' : 'warning'" @click="solelinggan">{{ issigntxt === 1 ?
            "已"
            : "未" }}标记手机屏保</el-button>
          <el-button size="mini" type="primary" @click="nextpage">下一张</el-button>
        </li>
      </template>
    </ImgPreview>
  </div>
</template>

<script>
import { optionSdImageOsTag, optionSdModel, optionSdImageOs } from '@/api/aiimg'
import ImgPreview from '@/components/ImgPreview/ImgPreview.vue'
export default {
  name: 'TypeImgList',
  components: { ImgPreview },
  data() {
    return {
      tableData: [],
      name: '',
      pageNum: 1,
      total: 0,
      form: {
        // 0 查询 3 删除
        option: 0,
        modelId: '', // 'sd_model.id，使用的模型版本id',
        modelName: '',
        platform: '',
        pageSize: 20,
        id: '', // 删除直接传id
        sdTagsId: '',   //标签id
        osStatus: '', // 0未标记 1已标记
        is18: '',
        negativePrompt: '',
        sdTagsId:''
      },
      mod: '',
      modlist: [],
      batchid: '',
      platform: '',
      sdTagIds: [],
      urlstatus: '',
      sdImageTypeIds: [],
      platformlist: [],
      sdTagIdlist: [],
      sdImageTypeIdlist: [],
      preivewdialog: false,
      keyvalue: 0,
      selectlist: [],
      currentnum: 0,
      tagId: '',
      tagIdList: '',
      bj: '',
      is18type: 2,
    }
  },
  computed: {
    issigntxt() {
      let str = ''
      str = this.tableData[this.currentnum]?.osStatus
      return str
    }
  },
  watch: {
    sdTagIds: {
      handler() {
        this.keyvalue++
      },
      deep: true
    },
    // sdImageTypeIds: {
    //   handler() {
    //     this.keyvalue++
    //   },
    //   deep: true
    // }
  },
  created() {
    this.getSdTags()
    this.getmod()
    this.getlist()
  },
  methods: {
    is18Type() {
      this.form.is18 = this.is18
    },
    currentnumChange(v) {
      this.currentnum = v
      this.tagId = this.tableData[this.currentnum]?.sdTagsId || ''
    },
    nextpage() {
      const el = this.$refs.ImgPreview
      el.next && el.next()
    },
    solelinggan() {
      const el = this.$refs.ImgPreview
      const data = el.currentimg
      this.lingganbiaoji([data])
    },
    previewClose() {
      this.preivewdialog = false
    },
    previewShow(scope) {
      this.currentnum = scope.$index
      this.preivewdialog = true
      this.tagId = this.tableData[this.currentnum]?.sdTagsId || ''
    },
    handlelinggan() {
      this.lingganbiaoji('')
    },
    lingganbiaoji(arr) {
      const arrlist = arr?.length > 0 ? arr : this.selectlist
      // 灵感标记
      const list = arrlist.map(i => {
        const item = {
          ...i
        }
        item.osStatus = item.osStatus === 1 ? 0 : 1
        item.sdTagsId = this.tagId
        item.is18 = this.is18type
        return item
      })
      const data = {
        option: 2,
        list
      }
      optionSdImageOs(data).then(res => {
        if (res.code === 200) {
          this.$message.success('操作成功')
          this.search('biaoji')
        }
      })
    },
    handleSelectionChange(val) {
      this.$set(this, 'selectlist', val)
    },
    async getSdTags() {
      const res = await optionSdImageOsTag({ option: 0 })
      if (res.code === 200 && res.data) {
        this.sdTagIdlist = [{ id: '', name: '全部', children: [...res.data] }]
        this.tagIdList = res.data;
      }
    },
    async getmod() {
      const res = await optionSdModel()
      if (res.code === 200 && res.data) {
        this.modlist = res.data
      }
    },
    search(type) {
      this.clearForm()
      if (type !== 'biaoji') {
        this.pageNum = 1
      }
      this.form.osStatus = this.bj
      this.form.batchid = this.batchid
      this.form.modelId = this.mod
      this.form.sdTagIds = this.sdTagIds.map(i => i[1])
      this.getlist()
    },
    deltype(row) {
      this.clearForm()
      this.form = {
        ...row
      }
      this.form.option = 3
      this.form.status = 2
      this.getlist()
    },
    async getlist() {
      const data = {
        ...this.form
      }
      if (data.option === 0) {
        delete data.status
        data.osStatus = this.bj
        this.tableData = []
      }
      console.log(data);
      data.pageNum = this.pageNum
      const res = await optionSdImageOs(data)
      if (data.option === 0) {
        if (res.code === 200 && res?.data) {
          this.tableData = res.data.list
          this.total = res.data.total
          //console.log('当前数据：' +res.data.list[this.currentnum]?.is18);
          this.is18type = res.data.list[this.currentnum]?.is18 || 1
          //console.log('18:'+ this.is18type);

        }
      } else {
        this.$message.success('操作成功')
        this.clearForm()
        this.getlist()
      }
    },
    clearForm() {
      this.form = {
        // 0 查询 3 删除
        option: 0,
        modelId: '', // 'sd_model.id，使用的模型版本id',
        modelName: '',
        platform: '',
        pageSize: 20,
        id: '', // 删除直接传id
        sdTagsId: '',   //标签id
        osStatus: '', // 0未标记 1已标记
        is18: '',
        sdTagsId:''
      }
    },
    handleSizeChange(val) {
      // 改变每页条数
      this.form.pageSize = val
      this.getlist()
    },
    handleCurrentChange(val) {
      // 改变页数
      this.$set(this, 'pageNum', val)
      this.getlist()
    }
  }
}
</script>

<style lang='less' scoped></style>
