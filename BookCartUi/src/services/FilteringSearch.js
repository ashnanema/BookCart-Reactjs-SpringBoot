class FilteringSearch
{
  getinput(searchinput)
  {
//    console.log(searchinput);
   if(searchinput.author==='' && searchinput.title==='' && searchinput.isbnno==='')
   {
     return "http://localhost:8083/v1/books";
   }
   else if(searchinput.title!=='' && searchinput.author==='' && searchinput.isbnno==='')
   {
       return `http://localhost:8083/v1/books?bookTitle=${searchinput.title}`;
   }
   else if(searchinput.title==='' && searchinput.author!=='' && searchinput.isbnno==='')
   {
       return `http://localhost:8083/v1/books?authorName=${searchinput.author}`;
   }
   else if(searchinput.title==='' && searchinput.author==='' && searchinput.isbnno!=='')
   {
       return `http://localhost:8083/v1/books?isbnNo=${searchinput.isbnno}`;
   }
   else if(searchinput.title!=='' && searchinput.author!=='' && searchinput.isbnno==='')
   {
       return `http://localhost:8083/v1/books?authorName=${searchinput.author}&bookTitle=${searchinput.title}`;
   }
   else if(searchinput.title==='' && searchinput.author!=='' && searchinput.isbnno!=='')
   {
       return `http://localhost:8083/v1/books?authorName=${searchinput.author}&isbnNo=${searchinput.isbnno}`;
   }
   else if(searchinput.title!=='' && searchinput.author==='' && searchinput.isbnno!=='')
   {
    return `http://localhost:8083/v1/books?bookTitle=${searchinput.title}&isbnNo=${searchinput.isbnno}`;
   }
   else
   {
    return `http://localhost:8083/v1/books?authorName=${searchinput.author}&bookTitle=${searchinput.title}&isbnNo=${searchinput.isbnno}`;
   }

  }

  fetchingData(urlinput)
  {
      
    fetch(urlinput).then((res)=>{
        if(!res.ok)
        {
            throw Error("unable to connect with the databse");
        }
        return res.json();
    })
    .then((data)=>{
        console.log(data);
      
    })
    .catch((err)=>{
        // seterror(err.message);
        // console.log(error);

    });

  }

};
export default new FilteringSearch();