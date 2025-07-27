import React from 'react';

class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.itemname = props.itemname;
    this.price = props.price;
  }

  render() {
    return (
      <tr>
        <td>{this.props.itemname}</td>
        <td>{this.props.price}</td>
      </tr>
    );
  }
}

export default Cart;
