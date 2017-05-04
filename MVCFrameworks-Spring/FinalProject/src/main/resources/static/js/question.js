$(function () {

    if($("#onLoad").length){
        loadQuestion();
    }

});

function loadQuestion() {
    var challengedUserId = window.location.pathname.split("/")[3];
    if (challengedUserId === undefined) {
        challengedUserId = 0;
    }
    $.ajax({
        type: 'GET',
        url: '/fight/progress/' + challengedUserId,
        data: 'json',
        success: function (usersFight) {
            if (usersFight.length === 0) {
                loadRandomQuestion();
            } else {
                loadQuestionById(usersFight.question.id, usersFight)
            }
        }
    });


}

function loadRandomQuestion() {
    $.ajax({
        type: 'GET',
        url: '/questions/get',
        data: 'json',
        success: function (question) {
            if (window.location.pathname.search("/users/") > -1) {
                saveFight(question);
            } else {
                addQuestion(question)
            }

        }
    })
}

function loadQuestionById(questionId, usersFight) {
    $.ajax({
        type: 'GET',
        url: '/questions/get/' + questionId,
        data: 'json',
        success: function (question) {
            addQuestion(question, usersFight);
        }
    })
}

function disableButtons() {
    $('#answerOne').attr("disabled", "disabled");
    $('#answerTwo').attr("disabled", "disabled");
    $('#answerThree').attr("disabled", "disabled");
    $('#answerFour').attr("disabled", "disabled");


}

function isGivenAnsweredCorrect(obj, rightAnswer) {
    if (obj.text() === rightAnswer) {
        obj.css('background', 'green');
        return true;
    } else {
        obj.css('background', 'red');

        $('button:contains(' + rightAnswer + ')').css('background-color', 'green');
        return false;
    }
}

function addQuestion(question, usersFight) {
    var context = question.context;
    var rightAnswer = question.rightAnswer;
    var wrongAnswerOne = question.wrongAnswerOne;
    var wrongAnswerTwo = question.wrongAnswerTwo;
    var wrongAnswerThree = question.wrongAnswerThree;
    var answers = [rightAnswer, wrongAnswerOne, wrongAnswerTwo, wrongAnswerThree];
    answers = shuffle(answers);
    $('#context').text(context);
    $('#answerOne').click(function () {
        var isAnsweredCorrect = isGivenAnsweredCorrect($(this), rightAnswer);
        disableButtons();
        updateFight(usersFight, isAnsweredCorrect);
    }).text(answers[0]);
    $('#answerTwo').text(answers[1]).on('click', function () {
        var isAnsweredCorrect = isGivenAnsweredCorrect($(this), rightAnswer);
        disableButtons();
        updateFight(usersFight, isAnsweredCorrect);
    });
    $('#answerThree').text(answers[2]).on('click', function () {
        var isAnsweredCorrect = isGivenAnsweredCorrect($(this), rightAnswer);
        disableButtons();
        updateFight(usersFight, isAnsweredCorrect);
    });
    $('#answerFour').text(answers[3]).on('click', function () {
        var isAnsweredCorrect = isGivenAnsweredCorrect($(this), rightAnswer);
        disableButtons();
        updateFight(usersFight, isAnsweredCorrect);
    });
    resizeButton();
}

function resizeButton() {

    var highestBox = 0;
    $(' .questionAnswer').each(function () {
        if ($(this).height() > highestBox) {
            highestBox = $(this).height();
        }
    }).height(highestBox);

}

function shuffle(array) {
    var currentIndex = array.length, temporaryValue, randomIndex;

    while (0 !== currentIndex) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;

        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }

    return array;
}

function saveFight(question) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
    var challengedUserId = window.location.pathname.split("/")[3];
    $.ajax({
        type: 'PUT',
        url: '/fight/save/' + challengedUserId,
        data: JSON.stringify(question),
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (usersFight) {
            addQuestion(question, usersFight);
        }

    });

}

function updateFight(userFight, isAnsweredCorrect) {
    var pathname = window.location.pathname;
    if (pathname.search("/users/") > -1) {
        var otherUserId = pathname.split("/")[3];
        if (userFight.challenger.id == otherUserId) {

            userFight.isChallengedUserAnswered = true;
            userFight.isChallengedAnsweredCorrect = isAnsweredCorrect;
        } else {
            userFight.isChallengerUserAnswered = true;
            userFight.isChallengerAnsweredCorrect = isAnsweredCorrect;
        }

        sendUpdatedFight(userFight);
    }
}

function sendUpdatedFight(userFight) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
    $.ajax({
        type: 'PUT',
        url: '/fight/update',
        data: JSON.stringify(userFight),
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });
}

    
