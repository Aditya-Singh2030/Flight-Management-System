import React from 'react';
import { Container, Tabs, Tab, Card, ListGroup, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const mockBookings = [
    {id: 'BK987', flight: 'UA123', from: 'JFK', to: 'LAX', date: '2024-10-15', status: 'Upcoming'},
    {id: 'BK654', flight: 'BA245', from: 'LHR', to: 'JFK', date: '2024-05-20', status: 'Completed'}
];

const UserDashboardPage = () => {
  return (
    <Container>
      <h2 className="my-4">Welcome, User!</h2>
      <Tabs defaultActiveKey="history" id="dashboard-tabs" className="mb-3" fill>
        <Tab eventKey="history" title="Booking History">
          <Card>
            <Card.Header>Your Flights</Card.Header>
            <ListGroup variant="flush">
              {mockBookings.map(booking => (
                <ListGroup.Item key={booking.id}>
                    <div className="d-flex w-100 justify-content-between">
                        <h5 className="mb-1">{booking.from} to {booking.to}</h5>
                        <small>Status: {booking.status}</small>
                    </div>
                    <p className="mb-1">Flight {booking.flight} on {booking.date}</p>
                    {booking.status === 'Upcoming' && 
                        <Link to="/check-in">
                            <Button variant="info" size="sm">Go to Check-In</Button>
                        </Link>
                    }
                </ListGroup.Item>
              ))}
            </ListGroup>
          </Card>
        </Tab>
        <Tab eventKey="profile" title="Profile">
          <Card>
            <Card.Body>
                <p><strong>Name:</strong> John Doe</p>
                <p><strong>Email:</strong> john.doe@example.com</p>
                <p><strong>Frequent Flyer Number:</strong> FH12345678</p>
                <Button variant="secondary">Edit Profile</Button>
            </Card.Body>
          </Card>
        </Tab>
      </Tabs>
    </Container>
  );
};

export default UserDashboardPage;