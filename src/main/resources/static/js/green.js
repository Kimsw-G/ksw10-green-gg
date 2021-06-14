let tds = document.getElementsByTagName("td");

for (const td of tds) {
    if(td.className>12){
        td.style.backgroundColor = "#FF0000";
    }else if(td.className>9){
        td.style.backgroundColor = "#FF4000";
    }else if(td.className>6){
        td.style.backgroundColor = "#FF8000";
    }else if(td.className>3){
        td.style.backgroundColor = "#FFBF00";
    }else if(td.className>0){
        td.style.backgroundColor = "#FFFF00";
    }
}

let tdElm = document.getElementsByTagName("td");
let pElm = document.getElementsByTagName("p");
for(let i = 0;i<tdElm.length;i++){
    tdElm[i].addEventListener('mouseover', function() {
        pElm[i].style.display = 'block';
    })
    tdElm[i].addEventListener('mouseout', function() {
        pElm[i].style.display = 'none';
    })
}

// 카카오 API 파트
let JS_KEY = 'e0d2ca5173b1a8e1caeb895fcf89048b';
let WEB_LINK = 'http://49.50.167.14/';

Kakao.init(JS_KEY); // 초기화
function sendLink() {
    Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
            title: 'GREEN GG',
            description: '그 날, 롤 얼마나 했는지 궁금하다면?',
            imageUrl:
                'http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
            link: {
                mobileWebUrl: WEB_LINK,
                webUrl: WEB_LINK,
            },
        },
        
        buttons: [
            {
                title: '웹으로 보기',
                link: {
                    mobileWebUrl: WEB_LINK,
                    webUrl: WEB_LINK,
                },
            }
        ]
    })
}