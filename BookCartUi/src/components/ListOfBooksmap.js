import React from 'react'
import { useState } from 'react';
import bootstrap from 'bootstrap';
import Card from 'react-bootstrap/Card'
import NotifiactionToast from './NotifiactionToast';
import PopUp from "./PopUp";
import bookimage from '../assets/HarryPotter.PNG'
import Button from 'react-bootstrap/Button'
import '../styles/bookspreview.css'

  const ListOfBooksmap = ({booksList}) => {
  const [isOpen, setIsOpen] = useState(false);
  const[content,setcontent] = useState('');
  const[message,setmessage] = useState(null);
 
  const togglePopup = (title,author,price,noOfCopies,isbnNo, image) => {

    setIsOpen(!isOpen);
   
   
    const inputarray = {title,author,price,noOfCopies,isbnNo, image};
    setcontent(inputarray);
  }
    return ( 
      <div className='container'>
      
      
      
  
          {message && <NotifiactionToast message={message} setmessage={setmessage}/>} 
          

    {
         booksList.map(book=>(
          <Card style={{backgroundColor:'#e9ecef', borderColor:'black'}}>
        {/* //</Card>   <Card>   */}
        
          <Card.Img variant="top" src={book.image} />
          <Card.Text> {book.id}</Card.Text>
          <Card.Text>ISBN Number : {book.isbnNo}</Card.Text>
          <Card.Text>Title : {book.title}</Card.Text>
          <Card.Text>Author : {book.author}</Card.Text>
          <Card.Text>Price : {book.price}</Card.Text>
          {/* <Card.Text>{book.noOfCopies}</Card.Text> */}
         
       <Button variant ="success" onClick={()=>togglePopup(book.title,book.author,book.price ,book.noOfCopies, book.isbnNo)}>Show More</Button> 
       
      
       </Card> 
      
  
      
        ))   
  }
  
         
         
         
         
    {isOpen && <PopUp
      content={content}
      handleClose={togglePopup}
      setmessage = {setmessage}
    />}
    
     
     
    </div>
    )}
export default ListOfBooksmap;