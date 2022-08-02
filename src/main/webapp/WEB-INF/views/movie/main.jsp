<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/vue.js"></script>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
    <div id="app">
        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                                <a class="nav-link" href="#">Link</a>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model="title">
                            <button class="btn btn-success" type="button" @click="search">검색</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>

        <div class="card-group">
            <div class="card" v-for="item in movieList">
                <img :src="item.image" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title" v-html="item.title">({{item.pubDate}})</h5>
                    <h5 class="card-title">{{item.director}}</h5>
                </div>
                <div class="card-footer">
                    <small class="text-muted">평점 : {{item.userRating}}</small>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

<script>
    const app = new Vue({
        el:"#app",
        data: {
            message : '안녕',
            title: '',
            movieList: []
        },
        created() {
        },
        methods: {
            search: function() {
                console.log(this.title);

                fetch('http://localhost:8080/movie-list/' + encodeURIComponent(this.title))
                    .then((response) => {
                        return response.json();
                    })
                    .then((json) => {
                        console.log(json);
                        console.log(json.items);
                        this.movieList = json.items;
                    })
            }
        }
    })
</script>
