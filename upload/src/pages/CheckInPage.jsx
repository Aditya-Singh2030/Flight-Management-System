import React from 'react';
import { Container, Form, Button, Card, Row, Col, Alert } from 'react-bootstrap';

const CheckInPage = () => {
    const [checkedIn, setCheckedIn] = React.useState(false);
    
    const handleCheckIn = (e) => {
        e.preventDefault();
        setCheckedIn(true);
    }

  return (
    <Container>
      <h2 className="my-4">Online Check-In</h2>
      <Row className="justify-content-md-center">
        <Col md={8}>
          <Card className="p-4">
            {checkedIn ? (
                <Alert variant="success">
                    <h4>Check-In Successful!</h4>
                    <p>Your boarding pass has been sent to your registered email address. You can also view it in your dashboard.</p>
                </Alert>
            ) : (
            <Form onSubmit={handleCheckIn}>
              <Form.Group className="mb-3" controlId="bookingRef">
                <Form.Label>Booking Reference (PNR)</Form.Label>
                <Form.Control type="text" placeholder="e.g., AB12CD" required />
              </Form.Group>
              <Form.Group className="mb-3" controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Your last name" required />
              </Form.Group>
              <div className="d-grid">
                <Button variant="primary" type="submit">Find Booking & Check In</Button>
              </div>
            </Form>
            )}
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default CheckInPage;