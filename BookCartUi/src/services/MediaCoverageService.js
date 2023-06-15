import axios from 'axios';
const Book_Base_URL = "http://localhost:8083/v1/books/mediacoverage/";
class MediaCoverageService{

    getAllBooks(title)
    {
        const abc = axios.get(Book_Base_URL + title);
        console.log(abc);
    }
}
export default new MediaCoverageService();