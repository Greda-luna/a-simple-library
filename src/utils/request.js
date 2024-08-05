import axios from "axios"
import router from "@/router";
import Cookies from "js-cookie";


const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout:30000
})
//request拦截器
//可以自请求发送前对请求做一些处理
//比如统一加token，对请求参数统一加密
request.interceptors.request.use(config =>{
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    //let user = localStorage.getItem("user")? JSON.parse(localStorage.getItem("user"))
    //config.headers['Token'] = "token";//设置请求头
    /*const admin = Cookies.get('admin')
    if (!admin) {
        router.push('/login')
    }*/

    return config
}, error => {
    console.error('request error:'+error)// for debug
    return Promise.reject(error)
});

//response拦截器
//可以在接口响应后统一处理结果
request.interceptors.response.use(
    response =>{
        let res = response.data;
        //兼容服务端返回的字符串数据
        if (typeof res ==='string'){
            res = res ? JSON.parse(res) :res
        }
        return res;
    },
    error => {
        console.error('respnse error:'+error)//for debug
        return Promise.reject(error)
    }
)

export default request