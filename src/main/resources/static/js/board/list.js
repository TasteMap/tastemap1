function moveToDetail(iboard){
    location.href = '/board/detail?iboard=' + iboard;
};

//좋아요 영역
let favElem = document.querySelector('.fav');
let favIconElem = document.querySelector('#favIcon');
function event1(iboard){
    console.log('dd');
    if(favIconElem.classList.contains('far')) { // X > O
        insFavAjax(iboard);

    } else { // O > X
        delFavAjax(iboard);
    }
}
// favIconElem.addEventListener('click', function() {
//     console.log('dd');
//     if(favIconElem.classList.contains('far')) { // X > O
//         insFavAjax();
//
//     } else { // O > X
//         delFavAjax();
//     }
// });

function insFavAjax(iboard) {

    const param = {iboard};
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
                toggleFav(1);
            }else{
                alert('실패');
            }
        })
}

//좋아요 취소
function delFavAjax(iboard1) {
    const init = {
        method: 'DELETE'
    }
    const iboard = iboard1;

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
