import axios from "axios";

const BUY_API_URL = "http://localhost:8083/v1/books:buy"

class BuyService{

    buyBook(data){
      const promise = axios.post(BUY_API_URL,data);
      const datapromise = promise.then((res)=>res.data.responseMessage);
      return datapromise;
    }
}

export default new BuyService();