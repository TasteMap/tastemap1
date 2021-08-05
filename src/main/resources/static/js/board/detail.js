$(function() {
    //input을 datepicker로 선언
    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd' //달력 날짜 형태
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
        ,changeYear: true //option값 년 선택 가능
        ,changeMonth: true //option값  월 선택 가능
        ,currentText: '오늘 날짜'  // 오늘 날짜로 이동하는 버튼 패널
        ,closeText: '닫기' // 닫기 버튼 패널 dateFormat: "yy-mm-dd",
        ,showAnim: "slide" //애니메이션을 적용한다.
        ,showMonthAfterYear: true // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
        ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
        ,buttonText: "선택" //버튼 호버 텍스트
        ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
        //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전),minDate: "-5Y"
        ,maxDate: "+7D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        ,minDate: "0D"
    });
    //초기값을 오늘 날짜로 설정해줘야 합니다.
    $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});

// 각 주소마다 지도 띄우기
var address = $(".rsad").html();
var title = $(".title").html();
var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3
};

var map = new kakao.maps.Map(container, options);
// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        kakao.maps.event.addListener(marker, 'click', function() {
            //마커 클릭 시 길찾기로 연동
            window.location.href='https://map.kakao.com/link/to/' + title + ',' + coords.getLat() + ',' + coords.getLng();
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">길찾기</div>'
        });
        infowindow.open(map, marker);
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    }
});


//좋아요 여부 값 가져오기

var favCntElem = document.querySelector(".fav");

function getFavAjax() {
    fetch('fav/' + favCntElem.dataset.iboard)
        .then(function(res) {
            return res.json();
        })
        .then(function (myJson) {
            toggleFav(myJson.result);
        });
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

getFavAjax();

//코멘트

let cmtFrmElem = document.querySelector('#cmtFrm');
let cmtListElem = document.querySelector('#cmtList');
let cmtModModalElem = document.querySelector('#modal');


function regCmt(){
    let cmtVal = cmtFrmElem.cmt.value;
    let param = {
        iboard: cmtListElem.dataset.iboard,
        cmt : cmtVal
    };
    if(cmtVal == ''){
        alert('내용을 작성해주세요')
        return;
    }
    regAjax(param);
}

function regAjax(param){
    const init = {
        method: 'POST',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }
    };

    fetch('cmt', init)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson){
            console.log(myJson);

            switch(myJson.result){
                case 0: //등록실패
                    alert('등록 실패!');
                    break;
                case 1: //등록성공
                    cmtFrmElem.cmt.value = '';
                    getListAjax();
                    break;
            }
        });
}

function getListAjax(){
    var iboard = cmtListElem.dataset.iboard;

    fetch('cmt/' + iboard)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson){
            console.log(myJson);

            makeCmtElemList(myJson);
        });
}

function makeCmtElemList(data){
    //var cmtListElem = document.querySelector('#cmtList');
    cmtListElem.innerHTML = ''; // 여기서 innertext줘도 되고 이거 줘도 됨

    var tableElem = document.createElement('table');
    tableElem.classList.add('table');
    var trElemTitle = document.createElement('tr');
    var thElemWriter = document.createElement('th');
    var thElemCtnt = document.createElement('th');
    var thElemRegdate = document.createElement('th');
    var thElemBigo = document.createElement('th');

    thElemWriter.innerText = '작성자';
    thElemCtnt.innerText = '내용';
    thElemRegdate.innerText = '작성일';
    thElemBigo.innerText = '비고';

    trElemTitle.append(thElemWriter);
    trElemTitle.append(thElemCtnt);
    trElemTitle.append(thElemRegdate);
    trElemTitle.append(thElemBigo);

    tableElem.append(trElemTitle);
    cmtListElem.append(tableElem);

    var loginUserPk = cmtListElem.dataset.loginUserPk;

    data.forEach(function(item){
        var trElemCtnt = document.createElement('tr');
        var tdElemImg = document.createElement('img');
        var tdElem1 = document.createElement('td');
        var tdElem2 = document.createElement('td');
        var tdElem3 = document.createElement('td');
        var tdElem4 = document.createElement('td');

        tdElemImg.setAttribute('src', `/pic/${item.iuser}/${item.profileImg}`);
        if(item.profileImg == null){
            tdElemImg.setAttribute('src', `/img/noprofile.jpg`);
        }
        tdElemImg.className = 'tdimg';
        tdElem1.append(tdElemImg);
        tdElem1.append(item.writer);
        tdElem2.append(item.cmt);
        tdElem3.append(item.regdate);
        //일단 버튼은 스킵!
        if(parseInt(loginUserPk) === item.iuser){
            var delBtn = document.createElement('button');
            var modBtn = document.createElement('button');

            //삭제버튼 클릭시
            delBtn.addEventListener('click', function() {
                if(confirm('삭제하시겠습니까?')){
                    delAjax(item.icmt);
                }
            });

            //수정버튼 클릭시
            modBtn.addEventListener('click', function(){
                //댓글 수정 모달창 띄우기
                openModModal(item);
            });


            delBtn.innerText = '삭제';
            modBtn.innerText = '수정'; // modBtn.value는 안됨!

            tdElem4.append(delBtn);
            tdElem4.append(modBtn);
        }

        trElemCtnt.append(tdElem1);
        trElemCtnt.append(tdElem2);
        trElemCtnt.append(tdElem3);
        trElemCtnt.append(tdElem4);

        tableElem.append(trElemCtnt);
    })
}

function delAjax(icmt){
    fetch('cmt/' + icmt, {method: 'DELETE'})
        .then(function(res){
            return res.json();
        })
        .then(function(data){ // ->여기 data자체가 {result: 0} ->여기에 접근한다 그래서
            // 삭제가 잘됐으면 여기에 1이 넘어올꺼고 안됐으면 0이 넘어옴
            console.log(data);

            switch(data.result){
                case 0:
                    alert('댓글 삭제를 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });
}

function modAjax(){
    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    var param = {
        icmt: cmtModFrmElem.icmt.value,
        cmt: cmtModFrmElem.modCmt.value
    }

    const init = {
        method: 'PUT',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }
    };

    fetch('cmt', init)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson){

            closeModModal();
            switch(myJson.result){
                case 0:
                    alert('댓글수정을 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });

}

function openModModal({icmt, cmt}){
    cmtModModalElem.className = '';

    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    cmtModFrmElem.icmt.value = icmt;
    cmtModFrmElem.modCmt.value = cmt;
}

function closeModModal(){
    cmtModModalElem.className = 'displayNone';
}

getListAjax();