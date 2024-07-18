// 【关键】数据显示控制 前端分页
var table = $('#data-table').DataTable({
    "order": [[0, "asc"]],//默认ID排序
    "pageLength": 8, // 配置单页显示条数
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
        data: "city",
    }, {
        data: "subway",
    }, {
        data: "station",
    },{
        data: null
    }],
    columnDefs: [{
        targets: 4,
        render: function (data, type, row, meta) {
            var prefix = '<td class="f-14 td-manage">',
                content = '',
                suffix = '<a style="text-decoration:none" class="ml-5"' +
                    ' onClick="subway_del(this, ' + row.id + ')" href="javascript:;" title="删除"><i' +
                    ' class="Hui-iconfont">&#xe6e2;</i></a></td>';
            return prefix + content + suffix;
        }
    }],
    ajax: {
        type: "POST",
        url: "/admin/subways", // 服务器url
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

function subway_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'DELETE',
            url: '/admin/subway/delete/' + id,
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

function reloadTable() {
    table.ajax.reload(null, false);
}