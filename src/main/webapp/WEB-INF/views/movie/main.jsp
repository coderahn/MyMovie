<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/vue.js"></script>
    <title>Title</title>
    <meta charset="UTF-8">
    <style>
        .box_1 {
            float: left; width:100%;;
        }
        .box_2 {
            display:inline-block;
        }
    </style>
</head>
<body>
    <div id="app">
        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-fixed-top">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">영화 애플리케이션</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">문의사항</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="box_1">
            <form class="row g-3">
                <div class="col-md-6">
                    <label for="movieTitle" class="form-label">영화제목</label>
                    <input class="form-control me-2" id="movieTitle" type="search" placeholder="영화 제목을 입력해 주세요." aria-label="Search" v-model="reqData.title">
                </div>
                <div class="col-md-4">
                    <label for="country" class="form-label">국가</label>
                    <select id="country" class="form-select" v-model="reqData.country">
                        <option value="">전체</option>
                        <option v-for="(country, i) in initData.country" :id="'country_' + country.code" :key="i" :selected="i == 0" :value="country.code">
                            {{country.name}}
                        </option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="genre" class="form-label">장르</label>
                    <select id="genre" class="form-select" v-model="reqData.genre">
                        <%--<option v-for="(genre, i) in initData.country" selected>{{genre.name}}</option>--%>
                        <option value="">전체</option>
                        <option v-for="(genre, i) in initData.genre" :id="'genre_' + genre.code" :key="i" :selected="i == 0" :value="genre.code">
                            {{genre.name}}
                        </option>
                    </select>
                </div>
                <div class="col-12">
                    <button class="btn btn-success" type="button" @click="search">영화 검색</button>
                </div>
            </form>
        </div>
        <div class="box_2">
            <template v-if="movieList.length">
                <div class="row">
                    <div class="col-xs-6 col-md-3" v-for="item in movieList">
                        <div class="img-thumbnail">
                            <img :src="item.image" class="" alt="..." v-if="item.image">
                            <img src="/resources/img/readyImage.png" v-else>
                            <div class="caption">
                                <h5 v-html="item.title">({{item.pubDate}})</h5>
                                <h5>{{item.director}}</h5>
                                <small class="text-muted">평점 : {{item.userRating}}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
            <template v-else>
                <div class="jumbotron">
                    <h1>영화를 검색해 주세요!</h1>
                </div>
            </template>
        </div>
    </div>
</body>
</html>

<script>
    const app = new Vue({
        el:"#app",
        data: {
            initData: {
                country : [
                    {'name' : '한국', 'code' : 'KR'},
                    {'name' : '일본', 'code' : 'JP'},
                    {'name' : '미국', 'code' : 'US'},
                    {'name' : '홍콩', 'code' : 'HK'},
                    {'name' : '영국', 'code' : 'GB'},
                    {'name' : '프랑스', 'code' : 'FR'},
                    {'name' : '기타', 'code' : 'ETC'},
                ],
                genre : [
                    {'name' : '드라마', code : '1'},
                    {'name' : '판타지', code : '2'},
                    {'name' : '서부', code : '3'},
                    {'name' : '공포', code : '4'},
                    {'name' : '로맨스', code : '5'},
                    {'name' : '모험', code : '6'},
                    {'name' : '스릴러', code : '7'},
                    {'name' : '느와르', code : '8'},
                    {'name' : '컬트', code : '9'},
                    {'name' : '다큐멘터리', code : '10'},
                    {'name' : '코미디', code : '11'},
                    {'name' : '가족', code : '12'},
                    {'name' : '미스터리', code : '13'},
                    {'name' : '전쟁', code : '14'},
                    {'name' : '애니메이션', code : '15'},
                    {'name' : '범죄', code : '16'},
                    {'name' : '뮤지컬', code : '17'},
                    {'name' : 'SF', code : '18'},
                    {'name' : '액션', code : '19'},
                    {'name' : '무협', code : '20'},
                    {'name' : '애로', code : '21'},
                    {'name' : '서스펜스', code : '22'},
                    {'name' : '서사', code : '23'},
                    {'name' : '블랙코미디', code : '24'},
                    {'name' : '실험', code : '25'},
                    {'name' : '영화카툰', code : '26'},
                    {'name' : '영화음악', code : '27'},
                    {'name' : '영화패러디포스터', code : '28'}
                ]
            },
            reqData: {
              genre: '',
              title: '',
              country: '',
            },
            message : '안녕',
            movieList: []
        },
        created() {
        },
        methods: {
            search: function() {
                console.log(this.reqData.title);

                //타이틀 등 여러가지를 요청 파라미터 방식으로 넘기기
                //1)query:검색질의(UTF-8인코딩)
                //2)display:검색결과출력건수
                //3)start:검색 시작 위치
                //4)genre : 검색 장르
                //5)county : 검색국가
                //6)yearfrom : 영화최소 제작년도(최소)
                //7)yearto : 영화의 제작년도(최대)
                //body에 제이슨 직렬화해서 넘기기

                let query = Object.keys(this.reqData).map(key => (
                   encodeURIComponent(key) + '=' + encodeURIComponent(this.reqData[key])
                )).join('&');

                fetch('http://localhost:8080/movie-list?' + query)
                    .then((response) => {
                        return response.json();
                    })
                    .then((json) => {
                        console.log(json);
                        console.log(json.items);
                        json.items.map((item) => {
                            let directorArr = item.director.split('|');
                            item.director = directorArr.join(",").slice(0,-1);
                        });
                        this.movieList = json.items;
                    })
            }
        },
        watch : {
            //1)객체 내부 변경 감지 못함
            // reqData () {
            //     console.log('장르변경!', this.reqData.genre);
            // },
            //2)객체 내부 변경 감지 방법1
            // 'reqData.genre' () {
            //     console.log('장르변경!', this.reqData.genre);
            // },
            //3)객체 내부 변경 감지 방법2
            reqData: {
                deep: true,
                handler() {
                    console.log('reqData 장르 변경:', this.reqData.genre);
                }
            }
        }
    })
</script>
