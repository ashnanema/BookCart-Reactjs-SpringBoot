import axios from "axios";
import React, { createContext, useEffect, useState } from "react";

export const NewsContext = createContext();

export const NewsContextProvider = (props) => {
  const [data, setData] = useState();
  //   const apiKey = "487ba9c1f2684061876baf29f8fb3d70";

  useEffect(() => {
    axios
      .get(
        "http://localhost:8083/v1/books/mediacoverage/s"
      )
      .then((Response) => setData(Response.data))
      .catch((error) => console.log(error));
  }, [data]);

  return (
    <NewsContext.Provider value={{ data }}>
      {props.children}
    </NewsContext.Provider>
  );
};
