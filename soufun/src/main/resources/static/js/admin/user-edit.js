/**
 * Created by 瓦力.
 */
$(function () {
    //表单验证
    $("#form-user-edit").validate({
        rules: {
            name: {
                required: true,
                maxlength: 30
            },
            phoneNumber: {
                required: true,
            }
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.appendTo(element); //这里的element是录入数据的对象
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: 'post',
                url: '/admin/user/edit', // 提交地址
                success: function (data) {
                    if (data.code === 200) {
                        alert('提交成功！');
                        parent_reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.$('.btn-refresh').click();
                        parent.layer.close(index);
                        removeIframe();
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000});
                    }
                },
                error: function (request, message, e) {
                    layer.msg(request.responseText, {icon: 5, time: 2000});
                }
            });
            return false; //此处必须返回false，阻止常规的form提交
        }
    });
});