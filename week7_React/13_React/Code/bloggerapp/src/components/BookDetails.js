import React, { useState } from 'react';

const BookDetails = () => {
  const [showBooks, setShowBooks] = useState(true);

  const books = [
    { id: 1, title: 'Master React', price: 670 },
    { id: 2, title: 'Deep Dive into Angular 11', price: 800 },
    { id: 3, title: 'Mongo Essentials', price: 450 }
  ];

  return (
    <div className="simple-section">
      <h2>Book Details</h2>
      
      {/* Method 1: Conditional rendering with && operator */}
      {showBooks && (
        <div>
          {books.map(book => (
            <div key={book.id} className="simple-item">
              <h3>{book.title}</h3>
              <p>{book.price}</p>
            </div>
          ))}
        </div>
      )}

      {/* Method 2: Ternary operator */}
      <button onClick={() => setShowBooks(!showBooks)}>
        {showBooks ? 'Hide Books' : 'Show Books'}
      </button>

      {/* Method 3: Short-circuit evaluation */}
      <p>{books.length > 0 && `Total: ${books.length} books`}</p>
    </div>
  );
};

export default BookDetails;
