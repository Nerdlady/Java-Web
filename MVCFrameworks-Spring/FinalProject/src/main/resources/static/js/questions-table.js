loadData();



function loadData() {
    var pathname = window.location.pathname;
    if (pathname === '/admin/questions/all') {
        loadAllQuestions();
    } else if (pathname === '/admin/questions/approve') {
        loadApproveQuestions();
    }
}

function loadAllQuestions() {
    $.ajax({
        type: 'GET',
        url: '/admin/questions/all/data',
        data: 'json',
        success: function (questions) {
            $.each(questions, function (i, question) {
                addQuestionDOM(question);
            });

        }
    });
}

function loadApproveQuestions() {
    $.ajax({
        type: 'GET',
        url: '/admin/questions/approve/data',
        data: 'json',
        success: function (questions) {
            $.each(questions, function (i, question) {
                addQuestionDOM(question);
            });
        }
    });
}

function addQuestionDOM(question) {
    var id = question.id;
    var context = question.context;
    var rightAnswer = question.rightAnswer;
    var wrongAnswerOne = question.wrongAnswerOne;
    var wrongAnswerTwo = question.wrongAnswerTwo;
    var wrongAnswerThree = question.wrongAnswerThree;

   $('#questionsList')
        .append(
            $('<tr></tr>')
                .attr('questionid', id)
                .append(
                    $('<td/>')
                        .append($('<textarea/>')
                            .addClass('updateContextClass form-control col-sm-9')
                            .text(context)
                            .val(context)))
                .append(
                    $('<td/>')
                        .append($('<textarea/>')
                            .addClass('updateRightAnswerClass form-control')
                            .val(rightAnswer)))
                .append(
                    $('<td/>')
                        .append($('<textarea/>')
                            .addClass('updateWrongAnswerOneClass form-control ')
                            .val(wrongAnswerOne)))

                .append(
                    $('<td/>')
                        .append($('<textarea/>')
                            .addClass('updateWrongAnswerTwoClass form-control ')
                            .val(wrongAnswerTwo)))
                .append(
                    $('<td/>')
                        .append($('<textarea/>')
                            .addClass('updateWrongAnswerThreeClass form-control ')
                            .val(wrongAnswerThree)))
                .append($('<td/>')
                    .append(
                        $('<button/>')
                            .on('click', function () {
                                var currentDOMQuestion = $(this).parent().parent();
                                var questionId = currentDOMQuestion.attr('questionid');

                                deleteQuestion(questionId);
                                currentDOMQuestion.remove();
                            })
                            .attr("id", "buttonDelete")
                            .addClass("btn btn-info form-control")
                            .text("Delete")
                    ))
                .append($('<td/>')
                    .append(
                        $('<button/>')
                            .on('click', function () {
                                var pathname = window.location.pathname;

                                var currentDOMQuestion = $(this).parent().parent();
                                var question = {};
                                question.id = currentDOMQuestion.attr('questionid');
                                question.context = currentDOMQuestion.find('.updateContextClass').val();
                                question.rightAnswer = currentDOMQuestion.find('.updateRightAnswerClass').val();
                                question.wrongAnswerOne = currentDOMQuestion.find('.updateWrongAnswerOneClass').val();
                                question.wrongAnswerTwo = currentDOMQuestion.find('.updateWrongAnswerTwoClass').val();
                                question.wrongAnswerThree = currentDOMQuestion.find('.updateWrongAnswerThreeClass').val();
                                updateQuestion(question);
                                if (pathname === '/admin/questions/approve') {
                                    currentDOMQuestion.remove();
                                }
                            })
                            .attr("id", "buttonSave")
                            .addClass("btn btn-info form-control")
                            .text("Save")

                    ))
        );


}


function updateQuestion(question) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
    var questionId = question.id;
    $.ajax({
        type: 'PUT',
        url: '/admin/questions/edit/' + questionId,
        data: JSON.stringify(question),
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

}

function deleteQuestion(questionId) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
    $.ajax({
        type: 'DELETE',
        url: '/admin/questions/delete/' + questionId,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

}

function textAreaAdjust(o) {
    o.style.height = '0px';
    o.style.height = (10 + o.scrollHeight) + "px";
}




function resize() {

    $("textarea").each(function () {
        this.style.height = '0px';
        this.style.height = (this.scrollHeight + 10) + 'px';
    });
};
