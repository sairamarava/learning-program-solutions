import React, { useState } from 'react';
import './App.css';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import CourseDetails from './components/CourseDetails';

function App() {
  const [showAll, setShowAll] = useState(true);

  return (
    <div className="App">
      {/* Method 1: Simple conditional rendering with && */}
      {showAll && (
        <div className="container">
          <CourseDetails />
          <BookDetails />
          <BlogDetails />
        </div>
      )}

      {/* Method 2: Ternary operator */}
      <button onClick={() => setShowAll(!showAll)}>
        {showAll ? 'Hide All' : 'Show All'} Components
      </button>
    </div>
  );
}

export default App;
