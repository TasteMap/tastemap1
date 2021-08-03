
//슬라이드
new Swiper('.swiper-container', {

    slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
    spaceBetween : 30, // 슬라이드간 간격
    slidesPerGroup : 3, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

    // 그룹수가 맞지 않을 경우 빈칸으로 메우기
    // 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
    loopFillGroupWithBlank : true,

    // 무한 반복 loop : true,

    pagination : { // 페이징
        el : '.swiper-pagination',
        clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
    },
    navigation : { // 네비게이션
        nextEl : '.swiper-button-next', // 다음 버튼 클래스명
        prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
    },
});

//룰렛
(function($) {
    const taste = document.querySelector('.taste');
    const bg = document.querySelector('.bg');
    const rl = document.querySelector('#rl');
    const close_btn = document.querySelector('.close_btn');

    taste.addEventListener('click', () => {
        bg.style.display = 'block';
        rl.style.display = 'block';
    })

    close_btn.addEventListener('click', () => {
        bg.style.display = 'none';
        rl.style.display = 'none';
    })

    bg.addEventListener('click', () => {
        bg.style.display = 'none';
        rl.style.display = 'none';
    })

    $.fn.extend({

        roulette: function(options) {

            var defaults = {
                angle: 0,
                angleOffset: -45,
                speed: 5000,
                easing: "easeInOutElastic",
            };

            var opt = $.extend(defaults, options);

            return this.each(function() {
                var o = opt;

                var data = [
                    { color: '#ffd9b3', text: '한식'},
                    { color: '#ff751a', text: '일식'},
                    { color: '#ff8533', text: '중식'},
                    { color: '#ff944d', text: '동남아식'},
                    { color: '#ffa366', text: '양식'},
                    { color: '#ffbf80', text: '분식'},
                ];

                var $wrap = $(this);
                var $btnStart = $wrap.find("#btn-start");
                var $roulette = $wrap.find(".roulette");
                var wrapW = $wrap.width();
                var angle = o.angle;
                var angleOffset = o.angleOffset;
                var speed = o.speed;
                var esing = o.easing;
                var itemSize = data.length;
                var itemSelector = "item";
                var labelSelector = "label";
                var d = 360 / itemSize;
                var borderTopWidth = wrapW;
                var borderRightWidth = tanDeg(d);

                for (i = 1; i <= itemSize; i += 1) {
                    var idx = i - 1;
                    var rt = i * d + angleOffset;
                    var itemHTML = $('<div class="' + itemSelector + '">');
                    var labelHTML = '';
                    labelHTML += '<p class="' + labelSelector + '">';
                    labelHTML += '  <span class="text">' + data[idx].text + '<\/span>';
                    labelHTML += '<\/p>';

                    $roulette.append(itemHTML);
                    $roulette.children("." + itemSelector).eq(idx).append(labelHTML);
                    $roulette.children("." + itemSelector).eq(idx).css({
                        "left": wrapW / 2,
                        "top": -wrapW / 2,
                        "border-top-width": borderTopWidth,
                        "border-right-width": borderRightWidth,
                        "border-top-color": data[idx].color,
                        "transform": "rotate(" + rt + "deg)"
                    });

                    var textH = parseInt(((2 * Math.PI * wrapW) / d) * .5);

                    $roulette.children("." + itemSelector).eq(idx).children("." + labelSelector).css({
                        "height": textH + 'px',
                        "line-height": textH + 'px',
                        "transform": 'translateX(' + (textH * 1.3) + 'px) translateY(' + (wrapW * -.3) + 'px) rotateZ(' + (90 + d * .5) + 'deg)'
                    });

                }

                function tanDeg(deg) {
                    var rad = deg * Math.PI / 180;
                    return wrapW / (1 / Math.tan(rad));
                }


                $btnStart.on("click", function() {
                    rotation();
                });

                function rotation() {

                    var completeA = 360 * r(5, 10) + r(0, 360);

                    $roulette.rotate({
                        angle: angle,
                        animateTo: completeA,
                        center: ["50%", "50%"],
                        easing: $.easing.esing,
                        callback: function() {
                            var currentA = $(this).getRotateAngle();

                            console.log(currentA);

                        },
                        duration: speed
                    });
                }

                function r(min, max) {
                    return Math.floor(Math.random() * (max - min + 1)) + min;
                }

            });
        }
    });
})(jQuery);

$(function() {
    $('.box-roulette').roulette();
});

//지도

// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    // if (!keyword.replace(/^\s+|\s+$/g, '')) {
    //     alert('키워드를 입력해주세요!');
    //     return false;
    // }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'),
        menuEl = document.getElementById('menu_wrap'),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = '';

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
            '<div class="info">' +
            '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
            '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>';
    }

    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
        '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

// 메인화면 지도 fade 적용하는 코드
$(function() {
    $("#fadeToggleBtn").on("click", function() {
        // id가 "divBox"인 요소를 1초에 걸쳐 점점 나타나게 하거나 사라지게 함.
        $("#divBox").fadeToggle(1000);
    });
});