//슬라이드
new Swiper('.swiper-container', {

    slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
    spaceBetween : 30, // 슬라이드간 간격
    slidesPerGroup : 3, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

    // 그룹수가 맞지 않을 경우 빈칸으로 메우기
    // 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
    loopFillGroupWithBlank : true,

    loop : true, // 무한 반복

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