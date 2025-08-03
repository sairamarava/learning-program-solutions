import React, { useState } from 'react';

const CourseDetails = () => {
  const [showDetails, setShowDetails] = useState(true);
  const [activeTab, setActiveTab] = useState('courses');

  const courses = [
    { id: 1, name: 'Angular', date: '4/5/2021' },
    { id: 2, name: 'React', date: '6/3/2021' }
  ];

  // Method 7: Switch statement for conditional rendering
  const renderContent = () => {
    switch (activeTab) {
      case 'courses':
        return courses.map(course => (
          <div key={course.id} className="simple-item">
            <h3>{course.name}</h3>
            <p>{course.date}</p>
          </div>
        ));
      case 'info':
        return <p>Course information and details</p>;
      default:
        return <p>Select a tab</p>;
    }
  };

  return (
    <div className="simple-section">
      <h2>Course Details</h2>
      
      {/* Method 8: Multiple conditional buttons */}
      <div className="tab-buttons">
        <button 
          className={activeTab === 'courses' ? 'active' : ''}
          onClick={() => setActiveTab('courses')}
        >
          Courses
        </button>
        <button 
          className={activeTab === 'info' ? 'active' : ''}
          onClick={() => setActiveTab('info')}
        >
          Info
        </button>
      </div>

      {/* Method 9: Conditional rendering with function call */}
      {showDetails && renderContent()}

      {/* Method 10: Null coalescing with conditional */}
      <p>{courses.length || 'No'} courses available</p>
      
      <button onClick={() => setShowDetails(!showDetails)}>
        {showDetails ? 'Hide' : 'Show'} Details
      </button>
    </div>
  );
};

export default CourseDetails;
