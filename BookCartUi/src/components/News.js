import React, { useContext } from "react";
import { NewsContext } from "../newsContext";
import NewsArticle from "./NewsArticle";

function News(props) {
  const { data } = useContext(NewsContext);
  console.log(data);
  return (
    <div>
      {data
        ? data.articles.map((news) => (
            <NewsArticle data={news} key={news.url} />
          ))
        : "Loading"}
    </div>
  );
}

export default News;
