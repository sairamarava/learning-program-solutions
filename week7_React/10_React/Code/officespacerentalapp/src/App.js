import './App.css';

function App() {

  const office = {
    name: "DBS",
    rent: 50000,
    address: "Chennai",
    image: "/office.jpeg"
  };

  const officeSpaces = [
    {
      id: 1,
      name: "DBS",
      rent: 50000,
      address: "Chennai",
      image: "/office-space.jpeg"
    },
    {
      id: 2,
      name: "Tech Hub",
      rent: 75000,
      address: "Bangalore",
      image: "/office-space.jpeg"
    },
    {
      id: 3,
      name: "Business Center",
      rent: 45000,
      address: "Mumbai",
      image: "/office-space.jpeg"
    },
    {
      id: 4,
      name: "Corporate Plaza",
      rent: 85000,
      address: "Delhi",
      image: "/office-space.jpeg"
    }
  ];

  // Function to determine rent color based on value
  const getRentColor = (rent) => {
    return rent < 60000 ? 'red' : 'green';
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      {/* Heading element */}
      <h1 style={{ textAlign: 'center', color: '#333', marginBottom: '30px' }}>
        Office Space, at Affordable Range
      </h1>

      {/* Display single office object */}
      <div style={{ 
        border: '1px solid #ddd', 
        borderRadius: '8px', 
        padding: '20px', 
        marginBottom: '30px',
        backgroundColor: '#f9f9f9'
      }}>
        <h2 style={{ color: '#333' }}>Featured Office Space</h2>
        <img 
          src={office.image || "/logo192.png"} 
          alt="Office Space" 
          style={{ 
            width: '200px', 
            height: '150px', 
            objectFit: 'cover',
            borderRadius: '5px',
            marginBottom: '15px'
          }} 
        />
        <h3 style={{ color: '#333' }}>Name: {office.name}</h3>
        <h3 style={{ color: getRentColor(office.rent) }}>
          Rent: Rs. {office.rent}
        </h3>
        <h3 style={{ color: '#333' }}>Address: {office.address}</h3>
      </div>

      {/* Display list of office spaces */}
      <div>
        <h2 style={{ color: '#333', marginBottom: '20px' }}>Available Office Spaces</h2>
        <div style={{ 
          display: 'grid', 
          gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', 
          gap: '20px' 
        }}>
          {officeSpaces.map((item) => (
            <div 
              key={item.id}
              style={{ 
                border: '1px solid #ddd', 
                borderRadius: '8px', 
                padding: '15px',
                backgroundColor: '#fff',
                boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
              }}
            >
              <img 
                src={item.image || "/logo192.png"} 
                alt={`Office Space ${item.name}`} 
                style={{ 
                  width: '100%', 
                  height: '150px', 
                  objectFit: 'cover',
                  borderRadius: '5px',
                  marginBottom: '10px'
                }} 
              />
              <h3 style={{ color: '#333', margin: '10px 0' }}>
                Name: {item.name}
              </h3>
              <h3 style={{ 
                color: getRentColor(item.rent), 
                margin: '10px 0',
                fontWeight: 'bold'
              }}>
                Rent: Rs. {item.rent}
              </h3>
              <h3 style={{ color: '#333', margin: '10px 0' }}>
                Address: {item.address}
              </h3>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
