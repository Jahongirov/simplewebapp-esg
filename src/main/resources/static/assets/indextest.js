//
// $("#test").click(function () {
//     var category = $("#category").val();
//     $.ajax({
//         url: '/user/test',
//         type: 'post',
//         data: {"category": category},
//         success: function (data) {
//             var testS = '';
//             var i = 0;
//             data.forEach(function (test) {
//                 testS = '';
//                 i++;
//                 testS += '<p>Savol:' + test.question + '<br>';
//                 var answers = '';
//                 test.answers.forEach(function (answer) {
//                     answers += answer.text + '<input type="radio" name="answer' + i + '" ' +
//                         'class="answer' + i + '" value="' + answer.id + '" data-test="' + test.id + '"><br>';
//                 });
//                 testS += answers + '</p>';
//                 $("#tests").append(testS);
//             });
//             $("#tests").append('<button id="tekshir">Tekshirish</button>');
//             $("#tekshir").click(function () {
//                 var answerIds = [];
//                 var testIds = [];
//                 for (var i = 0; i < data.length; i++) {
//                     answerIds.push($(".answer"+(i+1)+':checked').val());
//                     testIds.push($(".answer"+(i+1)+':checked').attr("data-test"));
//                 }
//                 $.ajax({
//                     url:'/user/test/check',
//                     type:'post',
//                     data:{"answerIds":answerIds,"testIds":testIds},
//                     success:function (data) {
//                         alert(data);
//                     },
//                     error: function (jqXHR, exception) {
//                         alert(jqXHR.status);
//                     }
//                 });
//             });
//
//         },
//         error: function (jqXHR, exception) {
//             alert(jqXHR.status);
//         }
//     });
// });


$("#test").click(function () {
    var category = $("#category").val();
    s = 1;
    // alert(s);
    $.ajax({
        url: '/index/test/test',
        type: 'post',
        data: {"category": category},
        success: function (data) {
            var testS = '';
            var retest='';
            var i = 0;
            data.forEach(function (test) {

                testS = '';
                i++;
                testS +=
                    '<div class="card-header stylish-color">Savol:&nbsp;&nbsp;' + test.question + '</div>';
                var answers = '';
                test.answers.forEach(function (answer) {
                    answers += '<div class="card-body"><input type="radio" name="answer ' + i + '" ' + ' class="answer' + i + '" value="' + answer.id + '" data-test="' + test.id + '">' + ' &nbsp;&nbsp; <span>' + answer.text + '</span></div>';
                });
                testS += answers + '';
                // $("#tests").append(testS);
                // testS='';
                // $("#testss").html(testS);
                retest+=testS;
            });
            $("#testss").html(retest);
            var retest1='<button id="tekshir" class="btn btn-dark">Tekshirish</button>';
            $("#testss").html(retest+retest1);
            // $("#tests").append('<button id="tekshir" class="btn btn-dark">Tekshirish</button>');
            $("#tekshir").click(function () {
                var answerIds = [];
                var testIds = [];
                for (var i = 0; i < data.length; i++) {
                    answerIds.push($(".answer" + (i + 1) + ':checked').val());
                    testIds.push($(".answer" + (i + 1) + ':checked').attr("data-test"));
                }
                $.ajax({
                    url: '/index/test/test/check',
                    type: 'post',
                    data: {"answerIds": answerIds, "testIds": testIds},
                    success: function (data) {
                        console.log(data);
                        var res = 0;
                        var res1 = 0;
                        data.resultIds.forEach(function (id) {
                            $("input[value='" + id + "']").next().css({"color": "green"});

                            var es = $("input[type=radio]");
                            console.log(es[0]);
                            // res++;
                            res1++;
                            for (var i = 0; i < es.length; i++) {
                                var e = $(es[i]);

                                if (e.is(':checked') && e.val() != id && e.next().css('color') != 'rgb(0, 128, 0)') {
                                    e.next().css({"color": "red"});

                                }
                                res = data.resultCount;

                            }

                        });
                        // $(".testNatija").class("invisible");
                        $("#savollar").text(res1);
                        $("#tjavoblar").text(res);
                        // alert("Umumiy:" + res1 + "/To'g'ri:" + res);

                    },
                    error: function (jqXHR, exception) {
                        alert(jqXHR.status);
                    }
                });
            });

        },
        error: function (jqXHR, exception) {
            alert(jqXHR.status);
        }
    });
});

