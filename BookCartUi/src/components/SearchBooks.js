import { useState } from "react";
import FilteringSearch from "../services/FilteringSearch";
import '../styles/search.css'
const SearchBooks = (props) => {
    const[isbnno ,setisbnno] = useState('');
    const[title ,settile] =useState('');
    const[author ,setauthor] =useState('');
    const handleSearch = (e)=>{
         e.preventDefault();
        const searchinput = {isbnno ,title ,author};
        
         const urlinput = FilteringSearch.getinput(searchinput);
         fetch(urlinput).then((res)=>{
            if(!res.ok)
            {
              props.setbooksList();                       
              return Promise.reject(res);
            }
            return res.json();
        })
        .then((data)=>{
             props.setbooksList(data);
             props.seterror(null);
        })
        .catch((response)=>{
            console.log(response);
            if(response.status===404)
            {
              props.seterror("No data found for the given input");
            }
        }); 
    }
    return ( 
        <div className="container sticky-top">
        <nav className="navbar navbar-dark bg-dark">
  <div className="container-fluid">
    <form className="d-flex" onSubmit={handleSearch}>
    <input 
            type="text" 
            placeholder="Book Title"
            value={title}
            onChange={(e) => settile(e.target.value)}
            style ={{marginRight:"20px"}}
          />

          <input 
            type="text" 
            placeholder="Book Author"
            value={author}
            onChange={(e) => setauthor(e.target.value)}
            style ={{marginRight:"20px"}}
          />
          <input 
            type="text" 
            placeholder="Book ISBN no."
            value={isbnno}
            onChange={(e) => setisbnno(e.target.value)}
            
          />
 <button style={{marginLeft:"20px"}} className="btn btn-success " >Search</button>

    </form>
    
  </div>
</nav>
</div>
     );
}
 
export default SearchBooks;