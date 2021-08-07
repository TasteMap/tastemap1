function moveToDetail(iboard){
    location.href = '/board/detail?iboard=' + iboard;
};
//https://www.daleseo.com/js-window-fetch/
//좋아요 영역
let favIconElem = document.querySelectorAll('.favIcon');

function event1(iboard, index) {
    console.log(index);
    console.log(typeof index);
    if (favIconElem[index].classList.contains('far')) { // X > O
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
            headers: {
                'accept': 'application/json',
                'content-type': 'application/json;charset=UTF-8'
            }
        };
        fetch('/board/fav?iboard='+iboard1, init)
            .then(function (res) {
                return res.json();
            })
            .then(function (myJson) {
                console.log("ins:" + myJson);
                if (myJson === 1) {
                    toggleFav(1, index);
                } else {
                    alert('실패');
                }
            })
    }

//좋아요 취소
    function delFavAjax(iboard1, index) {
        const init = {
            method: 'DELETE'
        }

        fetch('/board/fav?iboard=' + iboard1, init)
            .then(function (res) {
                return res.json();
            })
            .then(function (myJson) {
                console.log("del:" + myJson);
                if (myJson === 1) {
                    toggleFav(0, index);
                }
            })
    }

//좋아요 여부 값 가져오기
//     function getFavAjax(index) {
//         var iboard=document.querySelector('#iconIboard').dataset.iboard
//         console.log(iboard)
//         fetch(`/board/fav?iboard=${iboard}`)
//             .then(res => res.json())
//             .then(function (myJson) {
//                 console.log("myJson:"+myJson);
//                 toggleFav(myJson, index);
//             });
//     }

    function toggleFav(toggle, index) {
        console.log(favIconElem[index]);
        console.log(toggle);
        const elem = favIconElem[index]
        switch (toggle) {
            case 0: //좋아요 X
                // elem.classList.remove('fas');
                // elem.classList.add('far');
                elem.addClass('far');
                elem.removeClass('fas');
                break;
            case 1: //좋아요 O
                // elem.classList.remove('far');
                // elem.classList.add('fas');
                elem.addClass('fas');
                elem.removeClass('far');
                break;
        }
    }

// getFavAjax();