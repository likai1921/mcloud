<template>
  <div class="app-container">
    <ul class="header">
      <li>
        <el-input v-model="name" size="mini" placeholder="请输入名称" @keydown.enter="search" />
      </li>
      <li>
        模型：
        <el-select v-model="status" size="mini" filterable clearable placeholder="请选择状态" @change="search">
          <el-option v-for="item in statuslist" :key="item.status" :label="item.name" :value="item.status" />
        </el-select>
      </li>
      <li>
        <el-button size="mini" type="primary" @click="search">搜索</el-button>
      </li>
    </ul>
    <main>
      <el-table border :data="tableData" height="75vh">
        <el-table-column align="center" label="顺序" width="55" type="index" />
        <el-table-column align="center" prop="loraname" label="名称" />
        <el-table-column align="center" prop="title" label="中文名称" />
        <el-table-column align="center" prop="coverurl" label="封面图">
          <template slot-scope="scope">
            <img style="max-width: 100px;max-height: 100px" :src="scope.row.coverurl" alt="">
          </template>
        </el-table-column>
        <el-table-column align="center" prop="fromurl" label="数据来源url">
          <template slot-scope="scope">
            <a :href="scope.row.fromurl" style="color: blue" target="_blank">{{ scope.row.fromurl }}</a>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="tag" label="分类标签" />
        <el-table-column align="center" prop="lorakey" label="提示词格式" />
        <el-table-column align="center" prop="triggerword" label="约定触发词" />
        <el-table-column align="center" prop="type" label="类型" />
        <el-table-column align="center" prop="description" label="描述信息" />
        <el-table-column align="center" prop="status" label="状态">
          <template slot-scope="{ row }">
            <span v-if="statuslist && statuslist.length">{{ row.status | showstatus(statuslist) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" type="warning" @click="edittype(row)">修改</el-button>
            <el-button size="mini" type="danger" @click="deltype(row)">删除</el-button>
            <el-button size="mini" type="success" @click="addLoraStyle(row)">添加为测试模型</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="footer fr mt20">
        <el-pagination :page-sizes="[50, 100, 150, 200]" :page-size="form.pageSize" :current-page="pageNum"
          layout="total, sizes, prev, pager, next, jumper" :total="total" prev-text="上一页" next-text="下一页" background
          @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </main>
    <el-dialog :close-on-click-modal="false" :title="diaTitle1" :visible.sync="dialogVisible1" width="25%">
      <el-form ref="ruleForm" :model="form" :rules="rules">

        <el-form-item label="模型名称:" prop="modelid">
          <el-select v-model="form.modelid" size="mini" filterable clearable placeholder="请选模型" @change="modelchage">
            <el-option v-for="item in list" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="lora名称:">
          <el-input v-model="form.loraname" placeholder="lora名称" clearable />
        </el-form-item>

        <el-form-item label="风格修正提示词:">
          <el-input v-model="form.lorakey" placeholder="风格修正提示词" clearable />
        </el-form-item>
        <el-form-item label="风格名称">
          <el-input v-model="form.stylename" placeholder="请输入风格名称" clearable />
        </el-form-item>
        <el-form-item label="模型中文名称">
          <el-input v-model="form.modelnamechs" placeholder="请输入模型中文名称" clearable />
        </el-form-item>


        <el-form-item label="状态：" prop="status1">
          <template>
            <el-radio v-model="form.status1" :label="1" >正式记录</el-radio>
            <el-radio v-model="form.status1" :label="2">测试记录</el-radio>
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
import { optionLoraModel, optionLoraStaus, optionQueryLoarModel, optionSdModelStyle } from '@/api/aiimg'
export default {
  name: 'ImgType',
  filters: {
    showstatus(status, list) {
      return list.find(i => i.status === Number(status))?.name || ''
    }
  },
  data() {
    return {
      tableData: [],
      name: '',
      status1:2,
      form: {
        // 0 查询 1新增 2修改 3 删除
        option: 0,
        pageSize: 50,
        pageNum: 1,
        id: '',
        loraname: '', // 查询条件
        filename: '',
        title: '', // 中文封面
        coverurl: '', // '封面图'
        fromurl: '', // 数据来源的URLf地址
        status: '', // '模型记录状态， '0:不可用；1：国内可用；2：18+'
        tag: '', // lora的分类标签，数组格式
        lorakey: '', // LORA加入提示词的格式，如<lora:Moxin_Shukezouma:1>
        triggerword: '', // 约定触发词
        type: '', // 模型的类型，如：角色/风格
        remark: '',
        description: '', // 描述信息
        modelid: '',
        modelname: '',
        stylename: '',
        loraweight: '',
        modelnamechs: '',
        status1:2,
      },
      diaTitle: '',
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ],
        modelid: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ],
        status1: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      },
      dialogVisible: false,
      statuslist: [],
      status: '',
      total: 0,
      pageNum: 1,
      diaTitle1: '',
      dialogVisible1: false,
      loraLit: [],
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
      radio: '1'
    }
  },
  created() {
    this.getstatus()
    this.getlist()
  },
  methods: {
    statusChange(v) {
      console.log(v);
    },
    sure() {
      this.$refs['ruleForm'].validate(async (valid) => {
        if (valid) {
          this.data = {
            ...this.form
          }
          this.data.option = 1
          this.data.loraid = this.form.id
          this.data.id = ''
          this.data.adjustment = this.form.lorakey
          console.log(this.data)
          this.addStyle(this.data)
          this.clearForm()
          this.dialogVisible1 = false
        } else {
          console.log('error submit!!')
          return false
        }
      })

    },
    async addStyle(data) {
      const res = await optionSdModelStyle(data)

      if (res.code === 200) {
        this.$message.success('操作成功')
      } else {
        this.$message.success('操作失败')
      }

    },
    modelchage(v) {
      this.form.modelname = this.list.find(i => i.id === v).name
      console.log(this.form.modelname)
    },
    addLoraStyle(row) {
      this.form = {
        ...row
      }
      this.form.status=this.form.status1
      console.log(this.form)
      this.dialogVisible1 = true

    },
    getstatus() {
      optionLoraStaus().then(res => {
        if (res.code && res.data) {
          this.statuslist = res.data
        }
      })
    },
    search() {
      this.clearForm()
      this.pageNum = 1
      this.form.loraname = this.name
      this.form.status = this.status
      this.getlist()
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
      debugger
      this.form.option = 2
      this.diaTitle = '修改'
      this.dialogVisible = true
    },
    async getlist() {
      const data = {
        ...this.form
      }
      data.pageNum = this.pageNum
      const res = await optionLoraModel(data)
      if (data.option === 0) {
        if (res.code === 200 && res?.data) {
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
        pageSize: 50,
        pageNum: 1,
        id: '',
        loraname: '', // 查询条件
        filename: '',
        title: '', // 中文封面
        coverurl: '', // '封面图'
        fromurl: '', // 数据来源的URLf地址
        status: '', // '模型记录状态， '0:不可用；1：国内可用；2：18+'
        tag: '', // lora的分类标签，数组格式
        lorakey: '', // LORA加入提示词的格式，如<lora:Moxin_Shukezouma:1>
        triggerword: '', // 约定触发词
        type: '', // 模型的类型，如：角色/风格
        remark: '',
        description: '' // 描述信息
      }
      this.dialogVisible = false
      this.dialogVisible1 = false
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
