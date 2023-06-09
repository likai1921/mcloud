<template>
  <div class="app-container">
    <ul class="header">
      <li>
        <el-input v-model="name" size="mini" placeholder="请输入名称" @keydown.enter="search" />
      </li>
      <li>
        模型：
        <el-select v-model="modelname1" size="mini" filterable clearable placeholder="请选择模型" @change="search">
          <el-option v-for="item in list" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </li>

      <li>
        模型：
        <el-select v-model="querystatus" size="mini" filterable clearable placeholder="请选择状态" @change="search">
          <el-option v-for="item in statusquery" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </li>

      <li>
        <el-button size="mini" type="primary" @click="search">搜索</el-button>
      </li>
      <li class="ml20">
        <el-button size="mini" type="success" @click="addtype">新增</el-button>
      </li>
    </ul>
    <main>
      <el-table border :data="tableData" height="75vh">
        <el-table-column align="center" label="顺序" width="55" type="index" />
        <!-- <el-table-column align="center" prop="id" label="id" /> -->
        <el-table-column align="center" prop="stylecoverurl" label="图标">
          <template slot-scope="scope">
            <div style="display: flex; justify-content: center">
              <div>
                <img class="mr20 cp" style="max-width: 100px; max-height: 100px" :src="scope.row.stylecoverurl" alt="">
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="modelname" width="180" label="模型名称" />
        <el-table-column align="center" prop="stylename" label="风格名称" />
        <el-table-column align="center" prop="loraname" width="260" label="lora名称" />
        <el-table-column align="center" prop="loraweight" label="权重" />
        <el-table-column align="center" prop="modelnamechs" label="中文名称" />
        <el-table-column align="center" prop="remark" label="描述" />
        <el-table-column align="center" prop="adjustment" label="风格修正提示词" />
        <el-table-column align="center" prop="status" label="状态">
          <!-- <template slot-scope="{ row }">
            <el-switch v-model="row.status" active-text="有效" inactive-text="无效" :active-value="1" :inactive-value="0"
              active-color="#13ce66" inactive-color="#ff4949" @change="statusChange(row, 2)" />
          </template> -->

          <template  slot-scope="{ row }">
            <div>
              <el-radio v-model="row.status" :label="0" @change="statusChange(row, 0)">无效</el-radio>
              <el-radio v-model="row.status" :label="1" @change="statusChange(row, 1)">有效</el-radio>
              <el-radio style="margin-right: 30px;" v-model="row.status" :label="2" @change="statusChange(row, 2)">测试</el-radio>
            </div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" type="warning" @click="edittype(row)">修改</el-button>
            <el-button size="mini" type="danger" @click="deltype(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="footer fr mt20">
        <el-pagination :page-sizes="[50, 100, 150, 200]" :page-size="form.pageSize" :current-page="pageNum"
          layout="total, sizes, prev, pager, next, jumper" :total="total" prev-text="上一页" next-text="下一页" background
          @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </main>
    <el-dialog :close-on-click-modal="false" :title="diaTitle" :visible.sync="dialogVisible" width="45%">
      <el-form ref="ruleForm" :model="form" :rules="rules">
        <el-form-item prop="icon" label="图标：">
          <el-upload ref="upload" style="float: left" drag :action="this.$upload_url" :on-success="uploadSuccess"
            :file-list="fileList" list-type="picture">
            <i class="el-icon-upload" />
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
          </el-upload>
          <el-input v-model="form.stylecoverurl" size="mini" placeholder="请上传图片" disabled />
          <img v-if="form.stylecoverurl" style="width: 50px; height: 50px" :src="form.stylecoverurl" alt="">
        </el-form-item>
        <el-form-item label="模型名称">
          <el-select v-model="form.modelid" size="mini" filterable clearable placeholder="请选模型" @change="modelchage">
            <el-option v-for="item in list" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="lora名称">
          <el-select v-model="form.loraid" size="mini" filterable clearable placeholder="请选择lorad模型" @change="lorachage">
            <el-option v-for="item in loraLit" :key="item.id" :label="item.loraname" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="风格名称">
          <el-input v-model="form.stylename" placeholder="请输入风格名称" clearable />
        </el-form-item>
        <el-form-item label="lora权重">
          <el-input v-model="form.loraweight" placeholder="请输入lora权重" clearable />
        </el-form-item>
        <el-form-item label="模型中文名称">
          <el-input v-model="form.modelnamechs" placeholder="请输入模型中文名称" clearable />
        </el-form-item>

        <el-form-item label="描述信息">
          <el-input v-model="form.remark" placeholder="请输入描述信息" clearable />
        </el-form-item>
        <el-form-item label="风格修正提示词">
          <el-input v-model="form.adjustment" placeholder="风格修正提示词" clearable />
        </el-form-item>

        <el-form-item label="状态：" prop="status">
          <!-- <el-switch v-model="form.status" active-text="有效" inactive-text="无效" :active-value="1" :inactive-value="0"
            active-color="#13ce66" inactive-color="#ff4949" /> -->
            <template>
              <el-radio v-model="form.status" :label="0">无效</el-radio>
            <el-radio v-model="form.status" :label="1">有效</el-radio>
            <el-radio v-model="form.status" :label="2">测试</el-radio>
          </template>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="clearForm">取 消</el-button>
        <el-button type="primary" @click="sure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { optionSdModelStyle, optionQueryLoarModel } from '@/api/aiimg'
export default {
  name: 'ImgTag',
  data() {
    return {
      tableData: [],
      name: '',
      modelname1: '',
      querystatus:'',
      form: {
        // 0 查询 1新增 2修改 3 删除
        option: 0,
        id: '',
        modelid: '', // 模型id
        modelname: '', // 名称
        stylename: '', // 风格名称
        loraid: '', // 罗拉id
        loraname: '', // 罗拉名称
        loraweight: '', // 选择LORA的默认权重，风格强度
        modelnamechs: '', // 模型中文名称
        remark: '', // 描述
        status: '1', // 1有效 0无效
        pageSize: 50,
        adjustment: ''
      },
      diaTitle: '',
      pageNum: 1,
      total: 0,
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],

        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      },
      dialogVisible: false,
      list: [
        {
          id: 8,
          name: 'chilloutmix'
        }, {
          id: 11,
          name: 'anything-v3-fp32-pruned'
        }, {
          id: 19,
          name: 'Realistic_Vision_V1.4'
        }

      ],
      fileList: [],
      loraLit: [],
      statusquery: [
        {
          id: -1,
          name: '全部'
        }, {
          id: 0,
          name: '无效'
        }, {
          id: 1,
          name: '有效'
        } ,{
          id: 2,
          name: '测试'
        }

      ]
    }
  },
  created() {
    this.getlist()
    this.getLoraList()
  },
  methods: {
    lorachage(v) {
      this.form.loraname = this.loraLit.find(i => i.id === v).loraname
      // debugger
      console.log(this.form.loraname)
    },
    getLoraList() {
      optionQueryLoarModel().then(res => {
        this.loraLit = res.data
        console.log(this.loraLit)
      })
    },
    modelchage(v) {
      this.form.modelname = this.list.find(i => i.id === v).name
      console.log(this.form.modelname)
    },
    statusChange(row,status) {
      this.clearForm()
      this.form = {
        ...row
      }
      this.form.status =status
      console.log('###################')
      console.log(this.form)
      this.form.option = 2
      this.getlist()
    },
    search() {
      this.clearForm()
      this.form.modelnamechs = this.name
      this.form.modelid = this.modelname1
      this.form.status =this.querystatus
      console.log(this.form)
      this.getlist()
    },
    addtype() {
      this.clearForm()
      this.diaTitle = '新增'
      this.form.option = 1
      this.dialogVisible = true
    },
    deltype(row) {
      this.clearForm()
      this.form.id = row.id
      this.form.option = 3
      this.getlist()
    },
    edittype(row) {
      this.clearForm()
      this.form = {
        ...row
      }
      this.form.option = 2
      this.diaTitle = '修改'
      this.dialogVisible = true
    },
    sure() {
      this.$refs['ruleForm'].validate(async (valid) => {
        if (valid) {
          this.getlist()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    async getlist() {
      const data = {
        ...this.form
      }
      if (data.option === 0) {
        delete data.status
      }
      data.pageNum = this.pageNum
      data.querystatus =this.querystatus
      const res = await optionSdModelStyle(data)
      console.log(res)
      if (data.option === 0) {
        if (res.code === 200 && res?.data) {
          // debugger
          this.tableData = res.data.list
          this.total = res.data.total
        }
      } else {
        this.$message.success('操作成功')
        this.clearForm()
        this.getlist()
      }
    },
    clearForm() {
      this.form = {
        // 0 查询 1新增 2修改 3 删除
        option: 0,
        id: '',
        modelid: '', // 模型id
        modelname: '', // 名称
        stylename: '', // 风格名称
        loraid: '', // 罗拉id
        loraname: '', // 罗拉名称
        loraweight: '', // 选择LORA的默认权重，风格强度
        modelnamechs: '', // 模型中文名称
        remark: '', // 描述
        status: 1, // 1有效 0无效
        pageSize: 50,
        adjustment: ''
      }
      this.dialogVisible = false
    },
    uploadSuccess(res) {
      this.form.stylecoverurl = res.data
      setTimeout(() => {
        this.fileList.push({ url: this.form.stylecoverurl })
      }, 100)
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

