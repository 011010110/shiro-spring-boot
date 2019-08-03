/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form"], function (e) {
    var t = layui.$, i = layui.table,jQuery = layui.$;
    layui.form;
    var pager= {
        limit:5,
        groups:5,
        prev:"上一页",
        next:"下一页",
        first:"首页",
        last:"末页",
        layout:["first","prev","page","next","last","limit","skip","count","refresh"],
        curr:1
    };
    i.render({
        elem: "#LAY-user-manage",
        url: "/api/user/list",
        parseData:function(res){
            return {
                "code":res.code,
                "msg":res.msg,
                "data":res.data.records,
                "total":res.data.total
            };
        },
        request:{
            pageName:"current",
            limitName:"size"
        },
        response:{
            countName:"total"
        },
        cols: [[{type: "checkbox", fixed: "left"}, {
            field: "userName",
            title: "用户名",
            minWidth: 100
        }, {
            field: "phone",
            title: "手机"
        }, {field: "email", title: "邮箱"}, {field: "password", title: "密码"},
            {field: "jointime", title: "加入时间", sort: !0}, {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-useradmin-webuser"
        }]],
        page: pager,
        limit: 5,
        limits: [5,10,20],
        height: "full-220",
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-user-manage)", function (e) {
        var rowData = e.data;
        var id = rowData.id;
        if ("del" === e.event) {
            layer.confirm("真的删除行么", function (t) {
                var ids = [id];
                jQuery.ajax({
                    url:'/api/user/delete',
                    type:'post',
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //数据，json字符串
                    data : JSON.stringify(ids),
                    //请求成功
                    success : function(res) {
                        if(res.status){
                            i.reload('LAY-user-manage');
                            layer.msg('已删除');
                            e.del(), layer.close(t);
                        }else{
                            layer.msg(res.msg);
                        }
                    },
                    //请求失败，包含具体的错误信息
                    error : function(e){
                        layer.msg('操作失败');
                    }
                });

            })
        } else if ("edit" === e.event) {
            t(e.tr);
            layer.open({
                type: 2,
                title: "编辑用户",
                content: "/user/edit?id="+id,
                maxmin: !0,
                area: ["500px", "450px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e], r = "LAY-user-front-submit",
                        n = t.find("iframe").contents().find("#" + r);
                    l.layui.form.on("submit(" + r + ")", function (data) {
                        var field = data.field; //获取提交的字段
                        jQuery.post('/api/user/upate',field,function(res){
                            //是否保存成功
                            if(res.status){
                                layer.msg('保存成功');
                                layer.close(index); //关闭弹层
                                i.reload('LAY-user-manage');
                                i.reload('LAY-user-front-submit'); //数据刷新
                            }else{
                                layer.msg(res.msg, {
                                    offset: '15px'
                                    ,icon: 1
                                    ,time: 1000
                                });
                            }
                        })

                        i.reload("LAY-user-front-submit"), layer.close(e)
                    }), n.trigger("click")
                },
                success: function (e, t) {

                }
            })
        }
    }), i.render({
        elem: "#LAY-user-back-manage",
        url: layui.setter.base + "json/useradmin/mangadmin.js",
        cols: [[{type: "checkbox", fixed: "left"}, {field: "id", width: 80, title: "ID", sort: !0}, {
            field: "loginname",
            title: "登录名"
        }, {field: "telphone", title: "手机"}, {field: "email", title: "邮箱"}, {
            field: "role",
            title: "角色"
        }, {field: "jointime", title: "加入时间", sort: !0}, {
            field: "check",
            title: "审核状态",
            templet: "#buttonTpl",
            minWidth: 80,
            align: "center"
        }, {title: "操作", width: 150, align: "center", fixed: "right", toolbar: "#table-useradmin-admin"}]],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-user-back-manage)", function (e) {
        e.data;
        if ("del" === e.event) layer.prompt({formType: 1, title: "敏感操作，请验证口令"}, function (t, i) {
            layer.close(i), layer.confirm("确定删除此管理员？", function (t) {
                console.log(e), e.del(), layer.close(t)
            })
        }); else if ("edit" === e.event) {
            t(e.tr);
            layer.open({
                type: 2,
                title: "编辑管理员",
                content: "../../../views/user/administrators/adminform.html",
                area: ["420px", "420px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e], r = "LAY-user-back-submit",
                        n = t.find("iframe").contents().find("#" + r);
                    l.layui.form.on("submit(" + r + ")", function (t) {
                        t.field;
                        i.reload("LAY-user-front-submit"), layer.close(e)
                    }), n.trigger("click")
                },
                success: function (e, t) {
                }
            })
        }
    }), i.render({
        elem: "#LAY-user-back-role",
        url: layui.setter.base + "json/useradmin/role.js",
        cols: [[{type: "checkbox", fixed: "left"}, {field: "id", width: 80, title: "ID", sort: !0}, {
            field: "rolename",
            title: "角色名"
        }, {field: "limits", title: "拥有权限"}, {field: "descr", title: "具体描述"}, {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-useradmin-admin"
        }]],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-user-back-role)", function (e) {
        e.data;
        if ("del" === e.event) layer.confirm("确定删除此角色？", function (t) {
            e.del(), layer.close(t)
        }); else if ("edit" === e.event) {
            t(e.tr);
            layer.open({
                type: 2,
                title: "编辑角色",
                content: "../../../views/user/administrators/roleform.html",
                area: ["500px", "480px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e],
                        r = t.find("iframe").contents().find("#LAY-user-role-submit");
                    l.layui.form.on("submit(LAY-user-role-submit)", function (t) {
                        t.field;
                        i.reload("LAY-user-back-role"), layer.close(e)
                    }), r.trigger("click")
                },
                success: function (e, t) {
                }
            })
        }
    }), e("useradmin", {})
});