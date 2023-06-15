import BuyService from "../services/BuyService";
import MediaCoverageService from "../services/MediaCoverageService";

// This class is just for tesing purposes ignore this

const Testing = () => {

    // const data = {
    //     "bookId":17,
    //     "quantity":10

    // }
    const clickHandler = () =>{
        MediaCoverageService.getAllBooks("odio");
    }

    return (
        <button onClick={clickHandler}>check</button>
    )
} 

export default Testing