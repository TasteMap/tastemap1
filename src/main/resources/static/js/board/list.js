function moveToDetail(iboard){
    location.href = '/board/detail?iboard=' + iboard;
};
//https://www.daleseo.com/js-window-fetch/
//좋아요 영역
let favElem = document.querySelector('.fav');
let favIconElem = document.querySelectorAll('.favIcon');

function event1(iboard, index){
    console.log(index);
    console.log(typeof index);
    if(favIconElem[index].classList.contains('far')) { // X > O
        insFavAjax(iboard, index);
    } else { // O > X
        delFavAjax(iboard, index);
    }
}

function insFavAjax(iboard1, index) {
    const param = {iboard: iboard1};
    console.log(param);
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
            console.log(myJson);
            if(myJson === 1) {
                toggleFav(1, index);
            }else{
                alert('실패');
            }
        })
}

//좋아요 취소
function delFavAjax(iboard1, index) {
    const init = {
        method: 'DELETE'
    }
    const iboard = iboard1;

    fetch('fav?iboard=' + iboard1, init)
        .then(function(res) {
            return res.json();
        })
        .then(function (myJson) {
            if(myJson.result === 1) {
                toggleFav(0, index);
            }
        })
}

function toggleFav(toggle, index) {
    console.log(favIconElem[index]);
    switch(toggle) {
        case 0: //좋아요 X
            favIconElem[index].classList.remove('fas');
            favIconElem[index].classList.add('far');
            break;
        case 1: //좋아요 O
            favIconElem[index].classList.remove('far');
            favIconElem[index].classList.add('fas');
            break;
    }
}
