import React, { useState } from 'react';

const BlogDetails = () => {
  const [showContent, setShowContent] = useState(true);
  const [selectedBlog, setSelectedBlog] = useState(null);

  const blogs = [
    { id: 1, title: 'React Learning', author: 'Stephen Biz', content: 'Welcome to learning React!' },
    { id: 2, title: 'Installation', author: 'Schwerzdenier', content: 'You can install React from npm.' }
  ];

  // Method 4: If-else statement
  const renderBlogContent = () => {
    if (!showContent) {
      return <p>Content is hidden</p>;
    } else {
      return blogs.map(blog => (
        <div key={blog.id} className="simple-item" onClick={() => setSelectedBlog(blog)}>
          <h3>{blog.title}</h3>
          <p>{blog.author}</p>
          <p>{blog.content}</p>
        </div>
      ));
    }
  };

  return (
    <div className="simple-section">
      <h2>Blog Details</h2>
      
      {renderBlogContent()}

      {/* Method 5: Conditional rendering with && */}
      {selectedBlog && (
        <div className="selected-item">
          <h4>Selected: {selectedBlog.title}</h4>
          <button onClick={() => setSelectedBlog(null)}>Clear</button>
        </div>
      )}

      {/* Method 6: Ternary with complex condition */}
      <button onClick={() => setShowContent(!showContent)}>
        {showContent ? 'Hide' : 'Show'} Content
      </button>
    </div>
  );
};

export default BlogDetails;
