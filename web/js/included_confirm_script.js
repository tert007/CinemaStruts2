function confirmMessage(href){
        swal({
                    title: "Вы уверены?",
                    text: "Последующие изменения невозможно будет отменить!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: 'Да, удалить!',
                    cancelButtonText: "Нет, отменить!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function(isConfirm){
                    if (isConfirm){
                        swal({
                            title: "Удалено",
                            text: "Обьект успешно удален",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonText: 'Ок!'
                        }
                        ,
                        function(){
                            document.location.href = href;
                        });
                    } else {
                        swal("Отменено", "Удаление было успешно отменено", "error");
                    }
                });
    }