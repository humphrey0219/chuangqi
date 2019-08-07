var indexdata =
[

    {
        text: '页面管理', isexpand: true, children: [
            {url: "webpage/list", text: "所有页面"},
            {url: "webpage/add", text: "增加新页面"}
        ]
    },
    {
        text: '账号管理', isexpand: true, children: [
            {url: "account/addUi", text: "创建账户"},
            {url: "account/list", text: "所有账号"}            
        ]
    },
    {
        text: '页面图片管理', isexpand: true, children: [
            {url: "image/list", text: "所有图片"}
        ]
    },
    {
        text: '新闻管理', isexpand: true, children: [
            {url: "news/newslistUI?serviceType=10", text: "国内新闻"},
            {url: "news/newslistUI?serviceType=20", text: "行业新闻"}
        ]
    },
    {
        text: '留言管理', isexpand: true, children: [
            {url: "leave/leavelistUI", text: "留言列表"}
        ]
    }
];
