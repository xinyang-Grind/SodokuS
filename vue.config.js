module.exports={//扩展配置
    configureWebpack:{
        devServer:{
            port:8089,//更改端口号
            open:true,//自动启动浏览器
            //mock数据
            before(app){
                
            }
        }
    }
}