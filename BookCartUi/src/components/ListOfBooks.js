import { useEffect, useState } from "react";
import BookService from "../services/BookService";
import ListOfBooksmap from "./ListOfBooksmap";
import SearchBooks from "./SearchBooks";
import '../styles/listofbooks.css';
import Loading from "./Loading";
import Error from "./Error";
const ListOfBooks = () => {

    const [booksList ,setbooksList] =useState(null);
    const[error ,seterror] = useState(null);
    const[loading ,setLoading] = useState(true);
    
    
    useEffect(()=>{
        setTimeout(()=>{
            fetch('http://localhost:8083/v1/books').then((res)=>{
            if(!res.ok)
            {
                throw Error("unable to connect with the databse");
            }
            return res.json();
        })
        .then((data)=>{
            console.log(data);
          setbooksList(data);
          setLoading(false);
          
          
        })
        .catch((err)=>{
            seterror(err.message);
            console.log(error);
            setLoading(false);

        });

        },1000)
        
    },[]);
    return ( <>
    <div  id="books">
        <br />
       <SearchBooks setbooksList={setbooksList} seterror ={seterror} />
       <br />
       {loading &&<Loading/>}
       <br />
       {error && <Error error={error}/>}
      
      {booksList && <ListOfBooksmap  booksList= {booksList}/> }
     
    </div>
 
 </>
     );
}
 
export default ListOfBooks;