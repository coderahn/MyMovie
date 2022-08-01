<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/vue.js"></script>
    <title>Title</title>
</head>
<body>
    <div id="app">
        <div class="row row-cols-1 row-cols-md-2 g-4 container py-3">
            <div class="col" v-for="item in movieList">
                <div class="card">
                    <img :src="item.image" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">{{item.title}}</h5>
                        <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<!-- src="/resources/js/vue.js는 왜 안 됨?"-->
<script>
    var app = new Vue({
        el:"#app",
        data: {
            message : '안녕',
            movieList: []
        },
        created() {
            fetch('http://localhost:8080/movie-list')
                .then((response) => {
                    return response.json();
                })
                .then((json) => {
                    console.log(json);
                    console.log(json.items);
                    this.movieList = json.items;
                })
        }
    })
</script>
