import React, { useState } from 'react';
import { Container, Row, Col, Form, Button, Card, ListGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const mockFlights = [
  { id: 'UA123', from: 'JFK', to: 'LAX', departure: '08:00 AM', arrival: '11:30 AM', price: 350 },
  { id: 'DL456', from: 'JFK', to: 'LAX', departure: '10:00 AM', arrival: '01:30 PM', price: 375 },
];

const FlightSearchPage = () => {
  const [searchResults, setSearchResults] = useState([]);

  const handleSearch = (e) => {
    e.preventDefault();
    setSearchResults(mockFlights);
  };

  return (
    <Container>
      <h2 className="my-4">Search for a Flight</h2>
      <Card className="p-4 mb-4">
        <Form onSubmit={handleSearch}>
          <Row>
            <Col md={3} sm={6} className="mb-3">
              <Form.Group controlId="from">
                <Form.Label>From</Form.Label>
                <Form.Control type="text" placeholder="e.g., New York (JFK)" required />
              </Form.Group>
            </Col>
            <Col md={3} sm={6} className="mb-3">
              <Form.Group controlId="to">
                <Form.Label>To</Form.Label>
                <Form.Control type="text" placeholder="e.g., Los Angeles (LAX)" required />
              </Form.Group>
            </Col>
            <Col md={3} sm={6} className="mb-3">
              <Form.Group controlId="departureDate">
                <Form.Label>Departure Date</Form.Label>
                <Form.Control type="date" required />
              </Form.Group>
            </Col>
            <Col md={3} sm={6} className="d-flex align-items-end mb-3">
              <Button variant="primary" type="submit" className="w-100">Search</Button>
            </Col>
          </Row>
        </Form>
      </Card>

      {searchResults.length > 0 && (
        <>
          <h3>Search Results</h3>
          <ListGroup>
            {searchResults.map(flight => (
              <ListGroup.Item key={flight.id} className="d-flex justify-content-between align-items-center flex-wrap">
                <div className="mb-2">
                  <strong>{flight.id}</strong>: {flight.from} to {flight.to} <br />
                  <small>Departs: {flight.departure} | Arrives: {flight.arrival}</small>
                </div>
                <div className="text-end">
                  <h4>${flight.price}</h4>
                  <Link to={`/flight/${flight.id}`}>
                    <Button variant="info">View Details</Button>
                  </Link>
                </div>
              </ListGroup.Item>
            ))}
          </ListGroup>
        </>
      )}
    </Container>
  );
};

export default FlightSearchPage;