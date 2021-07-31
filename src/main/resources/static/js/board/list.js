function moveToDetail(iboard){
    location.href = '/board/detail?iboard=' + iboard;
};

//좋아요 영역
var favElem = document.querySelector('.fav');
var favIconElem = document.querySelector('#favIcon');
favIconElem.addEventListener('click', function() {
    if(favIconElem.classList.contains('far')) { // X > O
        insFavAjax();
    } else { // O > X
        delFavAjax();
    }
});

function insFavAjax() {
    const param = { iboard: favElem.dataset.iboard };
    const init = {
        method: 'POST',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }
    };
    fetch('fav', init)
        .then(function(res) {
            return res.json();
        })
        .then(function (myJson) {
            if(myJson.result === 1) {
                toggleFav(1);
            }
        })
}

//좋아요 취소
function delFavAjax() {
    const init = {
        method: 'DELETE'
    }
    const iboard = favElem.dataset.iboard;

    fetch('fav?iboard=' + iboard, init)
        .then(function(res) {
            return res.json();
        })
        .then(function (myJson) {
            if(myJson.result === 1) {
                toggleFav(0);
            }
        })
}

function toggleFav(toggle) {
    switch(toggle) {
        case 0: //좋아요 X
            favIconElem.classList.remove('fas');
            favIconElem.classList.add('far');
            break;
        case 1: //좋아요 O
            favIconElem.classList.remove('far');
            favIconElem.classList.add('fas');
            break;
    }
}
