// $(document).ready(function () {
//     $("#add_answer").click(function () {
//         //div qo'shtim probel qo'shtm
//         $("#answers").append('<p><input type="hidden" name="id">' +
//             '<input type="text" name="textAnswer" class="textAnswer" size="50"><input type="radio" name="isTrue">' +
//             ' <button type="button" class="removeAnswer">x</button></p>');
//         $(".removeAnswer").click(function () {
//             $(this).parent().remove();
//         });
//     });
//
//     $("#testForm").submit(function (e) {
//         e.preventDefault();
//         var id = $("#id").val();
//         var category = $("#category").val();
//         var question = $("#question").val();
//         var answers =   new Array();
//         var answersBool = new Array();
//
//         $(".textAnswer").each(function () {
//             answers.push(/*{"text":*/ $(this).val()/*, "isTrue": $(this).next().is(":checked")}*/);
//             answersBool.push($(this).next().is(":checked"));
//         });
//         console.log({"category": category, "question": question, "answers": answers});
//         $.ajax({
//             url: '/admin/test/save',
//             type: 'post',
//             data: {"id": id, "category": category, "question": question, "answers": answers,"answersBool":answersBool},
//             success: function (data) {
//                 alert(data);
//                 $("#category").val("");
//                 $("#question").val("");
//                 $("#answers").html("");
//             },
//             error: function (jqXHR, exception) {
//                 alert(jqXHR.status);
//             }
//         });
//     });
// });
// <input type="text" name="textAnswer" class="textAnswer"><input type="radio" name="isTrue">
$(document).ready(function () {
    $("#add_answer").click(function () {
        $("#answers").append('<p><input type="hidden" name="id">' +
            '<textarea name="textAnswer" class="textAnswer form-control" rows="3"></textarea><input type="radio" name="isTrue">' +
            ' <button type="button" class="removeAnswer form-control">x</button></p>');
        $(".removeAnswer").click(function () {
            $(this).parent().remove();
        });
    });

    $("#testForm").submit(function (e) {
        e.preventDefault();
        var id = $("#id").val();
        var category = $("#category").val();
        var question = $("#question").val();
        var answers =   new Array();
        var answersBool = new Array();

        $(".textAnswer").each(function () {
            answers.push(/*{"text":*/ $(this).val()/*, "isTrue": $(this).next().is(":checked")}*/);
            answersBool.push($(this).next().is(":checked"));
        });
        // console.log({"category": category, "question": question, "answers": answers});
        $.ajax({
            url: '/admin/test/save',
            type: 'post',
            data: {"id": id, "category": category, "question": question, "answers": answers,"answersBool":answersBool},
            success: function (data) {
                alert(data);
                $("#category").val("");
                $("#question").val("");
                $("#answers").html("");
            },
            error: function (jqXHR, exception) {
                alert(jqXHR.status);
            }
        });
    });
});