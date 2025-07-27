import React from 'react';
import Cart from './Cart';

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    
    // Array of Cart items with 5 items
    this.cartItems = [
      { itemname: "Laptop", price: 80000 },
      { itemname: "TV", price: 120000 },
      { itemname: "Washing Machine", price: 50000 },
      { itemname: "Mobile", price: 30000 },
      { itemname: "Fridge", price: 70000 }
    ];
  }

  render() {
    return (
      <div className="mydiv">
        <h1>Items Ordered :</h1>
        <table border="1" style={{borderCollapse: 'collapse', margin: '20px auto'}}>
          <thead>
            <tr style={{backgroundColor: '#4CAF50', color: 'white'}}>
              <th style={{padding: '10px', border: '1px solid #ddd'}}>Name</th>
              <th style={{padding: '10px', border: '1px solid #ddd'}}>Price</th>
            </tr>
          </thead>
          <tbody>
            {this.cartItems.map((item, index) => (
              <Cart key={index} itemname={item.itemname} price={item.price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
