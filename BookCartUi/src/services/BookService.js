import axios from 'axios';
const Book_Base_URL = "http://localhost:8083/v1/books";
class BookService{

    getAllBooks()
    {
        return axios.get(Book_Base_URL);
    }
}
export default new BookService();