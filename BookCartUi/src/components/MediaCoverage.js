
// import PopUp from "./PopUp";
import { useEffect, useState } from "react";
import { NewsContextProvider } from "../newsContext";
import News from "./News";
import NewsArticle from "./NewsArticle";
const MediaCoverage = () => {
  const [fetchData, setFetchData] = useState(null);

  useEffect(()=>{
    fetch("http://localhost:8083/v1/books/mediacoverage/s").then((response) =>{
    return response.json();
  }).then((data) => {
    console.log(data);
    setFetchData(data);
  })
  },[]);
  
 
  return (
    // <NewsContextProvider>
      <div>
        Hello from App{
          fetchData &&  <NewsArticle data = {fetchData}/>
        }
        {/* <News /> */}
      </div>
    // </NewsContextProvider>
  );
};

export default MediaCoverage;

