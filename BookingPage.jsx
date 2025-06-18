import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const BookingPage = () => {
  const { state } = useLocation();
  const { flight } = state || {};
  const navigate = useNavigate();

  const formatDate = (date) => {
    if (!date) return '';
    const parsedDate = new Date(date);
    return parsedDate.toISOString().split('T')[0];
  };

  const [formData, setFormData] = useState({
    source: flight?.source || '',
    destination: flight?.destination || '',
    departureDate: flight?.departureDate ? formatDate(flight.departureDate) : '',
    noOfPassengers: 1,
    seatClass: 'Economy',
    passengerDetails: [{ fullName: '', email: '', phoneNo: '' }],
  });

  const [bookingSuccess, setBookingSuccess] = useState(null);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handlePassengerChange = (index, e) => {
    const updated = [...formData.passengerDetails];
    updated[index][e.target.name] = e.target.value;
    setFormData({ ...formData, passengerDetails: updated });
  };

  const addPassenger = () => {
    setFormData({
      ...formData,
      passengerDetails: [...formData.passengerDetails, { fullName: '', email: '', phoneNo: '' }],
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formattedDate = formatDate(formData.departureDate);
      const requestData = {
        source: formData.source,
        destination: formData.destination,
        departureDate: formattedDate,
        noOfPassengers: formData.noOfPassengers,
        seatClass: formData.seatClass,
        passengerDetails: formData.passengerDetails,
      };

      console.log('Booking Request:', requestData);
      const response = await axios.post('http://localhost:8081/booking/add', requestData, {
        headers: { 'Content-Type': 'application/json' }
      });

      console.log('Booking Success:', response.data);
      setBookingSuccess(response.data);
    } catch (error) {
      console.error('Booking Failed:', error);
      setBookingSuccess(false);
    }
  };

  const loadRazorpay = async (bookingId) => {
    try {
      const response = await fetch(`http://localhost:8090/payment/createOrder?bookingId=${bookingId}`, {
        method: "POST"
      });

      const order = await response.json();

      const options = {
        key: "rzp_test_95AXEbMQo373UD",
        amount: order.amount,
        currency: order.currency,
        name: "Flight Booking System",
        description: "Payment for booking",
        order_id: order.id,
        handler: function (response) {
          navigate(`/checkin?bookingId=${bookingId}`);
        },
        prefill: {
          name: formData.passengerDetails[0].fullName,
          email: formData.passengerDetails[0].email,
          contact: formData.passengerDetails[0].phoneNo
        },
        theme: {
          color: "#3399cc"
        }
      };

      const rzp = new window.Razorpay(options);
      rzp.open();
    } catch (error) {
      console.error("Payment Init Failed:", error);
      alert("❌ Payment failed to start");
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h2 className="text-2xl font-bold text-center">Book Your Flight</h2>

      {bookingSuccess === false ? (
        <p className="text-red-600 text-center text-lg">Booking Failed. Please Try Again!</p>
      ) : bookingSuccess ? (
        <div className="text-green-600 text-center text-lg">
          <p>Booking Confirmed! ✅</p>
          <p><strong>Booking ID:</strong> {bookingSuccess.bookingId}</p>
          <p><strong>Flight No:</strong> {bookingSuccess.flightNo}</p>
          <p><strong>Total Cost:</strong> ₹{bookingSuccess.totalCost}</p>

          <button
            onClick={() => loadRazorpay(bookingSuccess.bookingId)}
            className="bg-blue-600 text-white px-4 py-2 mt-4 rounded"
          >
            Pay Now
          </button>
        </div>
      ) : (
        <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mt-4 max-w-md mx-auto" onSubmit={handleSubmit}>
          <p><strong>From:</strong> {formData.source} → <strong>To:</strong> {formData.destination}</p>
          <p><strong>Departure Date:</strong> {formData.departureDate}</p>

          <label className="block mt-3">Number of Passengers:</label>
          <input
            type="number"
            name="noOfPassengers"
            value={formData.noOfPassengers}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded"
            min="1"
          />

          <label className="block mt-3">Seat Class:</label>
          <select
            name="seatClass"
            value={formData.seatClass}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded"
          >
            <option value="Economy">Economy</option>
            <option value="Business">Business</option>
            <option value="First Class">First Class</option>
          </select>

          {formData.passengerDetails.map((passenger, idx) => (
            <div key={idx} className="mt-4 p-4 border rounded">
              <h3 className="text-lg">Passenger {idx + 1}</h3>
              <input
                type="text"
                name="fullName"
                placeholder="Full Name"
                value={passenger.fullName}
                onChange={(e) => handlePassengerChange(idx, e)}
                className="w-full px-3 py-2 mb-2 border rounded"
                required
              />
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={passenger.email}
                onChange={(e) => handlePassengerChange(idx, e)}
                className="w-full px-3 py-2 mb-2 border rounded"
                required
              />
              <input
                type="tel"
                name="phoneNo"
                placeholder="Phone Number"
                value={passenger.phoneNo}
                onChange={(e) => handlePassengerChange(idx, e)}
                className="w-full px-3 py-2 border rounded"
                required
              />
            </div>
          ))}

          <button type="button" onClick={addPassenger} className="bg-gray-600 text-white mt-3 px-4 py-2 rounded">
            Add Another Passenger
          </button>

          <button type="submit" className="bg-green-600 text-white w-full py-2 mt-4 rounded hover:bg-green-700">
            Confirm Booking
          </button>
        </form>
      )}
    </div>
  );
};

export default BookingPage;
