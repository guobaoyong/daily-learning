// 【关键】数据显示控制 前端分页
var table = $('#data-table').DataTable({
    "order": [[0, "asc"]],//默认ID排序
    "pageLength": 2, // 配置单页显示条数
    "paging": true, // 关闭本地分页
    "lengthChange": false, // 允许用户改变表格每页显示的记录数
    "searching": true, // 允许Datatables开启本地搜索
    "ordering": true, // 启用Datatables排序
    "info": true, // 表格左边显示搜索信息
    "autoWidth": true, // 自动计算表格宽度
    "stateSave": false, // 允许表格缓存Datatables，以便下次恢复之前的状态
    "retrieve": true, // 如果已经初始化了，则继续使用之前的Datatables实例
    "processing": true, // 显示正在处理的状态
    "serverSide": false, // 服务器模式，数据由服务器掌控
    "pagingType": "simple_numbers", // 翻页显示: 上一页和下一页两个按钮，加上页数按钮
    "language": {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sUrl": "",
        "sEmptyTable": "未搜索到数据",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    },
    columns: [{ // 绑定数据源
        data: "id",
    }, {
        data: "name",
        searchable: true // 通过全局搜索框搜索标题
    }, {
        data: "email",
        searchable: false
    }, {
        data: "phoneNumber",
    }, {
        data: "lastLoginTime",
    }, {
        data: "avatar",
    },{
        data: "role",
    },{
        data: "status",
    },{
        data: null
    }],
    columnDefs: [{ // 定义表格样式
        targets: 4,
        render: function (data, type, row, meta) {
            return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
        }
    }, {
        targets: 5,
        render: function (data, type, row, meta) {
            return '<td><img class="picture-thumb" src="' + data + '?imageView2/1/w/200/h/100"></td>';
        }
    }, {
        targets: 6,
        render: function (data, type, row, meta) {
            var html = '';
            if (data === 'ADMIN') {
                html = '<td class="td-status"><span class="label label-danger radius">管理员</span></td>';
            } else if (data === 'USER') {
                html = '<td class="td-status"><span class="label label-success radius">普通用户</span></td>';
            }
            return html;
        }
    }, {
        targets: 7,
        render: function (data, type, row, meta) {
            var html = '';
            if (data === 1) {
                html = '<td class="td-status"><span class="label label-danger radius">封禁</span></td>';
            } else if (data === 0) {
                html = '<td class="td-status"><span class="label label-success radius">正常</span></td>';
            }
            return html;
        }
    }, {
        targets: 8,
        render: function (data, type, row, meta) {
            var prefix = '<td class="f-14 td-manage">',
                data_status = row.status,
                content = '',
                suffix = '<a style="text-decoration:none" class="ml-5"' +
                    ' onClick="user_edit(\'用户编辑\', \'/admin/user/edit?id=' + row.id + '\')" href="javascript:;"' +
                    ' title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5"' +
                    ' onClick="user_del(this, ' + row.id + ')" href="javascript:;" title="删除"><i' +
                    ' class="Hui-iconfont">&#xe6e2;</i></a></td>';
            if (data_status === 0) { // 正常
                content = '<a style="text-decoration:none" onClick="user_kill(this,' + row.id + ')"' +
                    ' href="javascript:;" title="封禁">封禁</a>&nbsp;';
            } else if (data_status === 1) { // 已发布
                content = '<a style="text-decoration:none" onClick="user_unkill(this,' + row.id + ')"' +
                    ' href="javascript:;" title="解封">解封</a>&nbsp;'
            }

            return prefix + content + suffix;
        }
    }],
    ajax: {
        type: "POST",
        url: "/admin/users", // 服务器url
        cache: false,
        data: function (data) {
            var postData = {}
            postData.draw = data.draw;
            postData.start = data.start;
            postData.length = data.length;
            return postData;
        }

    }
});

function user_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'PUT',
            url: '/admin/user/operate/' + id + '/' + '3',
            success: function (data) {
                if (data.code === 200) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    reloadTable();
                } else {
                    layer.msg('删除失败！' + data.message, {icon: 5, time: 1000});

                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                layer.msg('删除失败！' + jqXHR.responseText, {icon: 5, time: 3000});
            }
        });
    });
}

function user_kill(obj, id) {
    $.ajax({
        type: 'PUT',
        url: '/admin/user/operate/' + id + '/' + '1',
        success: function (data) {
            if (data.code === 200) {
                /*$(obj).parents("tr").remove();*/
                layer.msg('已封禁!', {icon: 1, time: 1000});
                reloadTable();
            } else {
                layer.msg('封禁失败！' + data.message, {icon: 5, time: 1000});

            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            layer.msg('封禁失败！' + jqXHR.responseText, {icon: 5, time: 3000});
        }
    });
}

function user_unkill(obj, id) {
    $.ajax({
        type: 'PUT',
        url: '/admin/user/operate/' + id + '/' + '2',
        success: function (data) {
            if (data.code === 200) {
                /*$(obj).parents("tr").remove();*/
                layer.msg('已解封!', {icon: 1, time: 1000});
                reloadTable();
            } else {
                layer.msg('解封失败！' + data.message, {icon: 5, time: 1000});

            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            layer.msg('解封失败！' + jqXHR.responseText, {icon: 5, time: 3000});
        }
    });
}

function reloadTable() {
    table.ajax.reload(null, false);
}

/* 编辑用户*/
function user_edit(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}