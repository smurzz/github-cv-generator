import { useState } from 'react';
import { Button, Form, InputGroup, Alert } from 'react-bootstrap';
import { Card, ListGroup, Badge } from 'react-bootstrap';

function Home() {

  const [login, setLogin] = useState('');
  const [resume, setResume] = useState(null);
  const [messageError, setMessErr] = useState('');
  const [languagesCards, setlanguagesCards] = useState(null);
  const [isReposFound, setIsReposFound] = useState(false);

  // action for the button "generate"
  const handleSubmit = async (e) => {
    e.preventDefault();
    getResumeByLogin()
    setLogin('');
  }

  // get an resume by login 
  function getResumeByLogin() {
    Promise.resolve(fetch(`http://localhost:8080/resume/${login}`))
      .then((response) => response.json())
      .then(data => {
        if (data.message === 'Not Found') {
          var messageError = (<Alert variant="danger">Error: GitHub account with the usename -- {login} -- is not found!</Alert>);
          setMessErr(messageError);
        } else {
          setResume(data);
          setIsReposFound(true);
          fillLanguagesCard(data.languages);
        }
      });
  }

  // create and fill container with cards of used languages and their percentages
  function fillLanguagesCard(languages){
    let languagesSorted = new Map([...Object.entries(languages)].sort((k, v) => v[1] - k[1]));
    let cards = [];
    languagesSorted.forEach((v, k) => {
      cards.push(
        <ListGroup.Item
          as="li"
          className="d-flex justify-content-between align-items-start" key={k}>
          <div className="ms-2 me-auto">
            <div className="fw-bold">{k}</div>
          </div>
          <Badge bg="primary" pill>
            ({Math.round(v)}%)
          </Badge>
        </ListGroup.Item>
      )
    });
    setlanguagesCards(cards);
  }

  return (
    !isReposFound ? (
      <main role="main" className="m-4 justify-items-center">
        <style>{'body{height: 100%; text-align: center; padding: 20% 20% 0rem 20%; }'}</style>
        <h1 className="cover-heading ">GitHub Resume Generator</h1>
        <p className="lead">Try to create your professional resume using your GitHub account. Enter your login and click "generate".</p>
        <InputGroup className="mb-3">
          <Form.Control
            placeholder="GitHub's login"
            aria-label="GitHub's login"
            aria-describedby="basic-addon2"
            size='sm'
            value={login}
            onChange={async (e) => { setLogin(e.target.value) }}
          />
          <Button variant="secondary" size='lg' onClick={handleSubmit}>
            Generate
          </Button>
        </InputGroup>
        {messageError}
      </main>
    ) : (
      <main role="main" className="m-1">
        <style>{'body{height: 100%; box-shadow: 5px 10px #888888; padding: 10rem 15rem 0rem 15rem; }'}</style>
        <style>{'main{box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px; padding: 2rem 4rem 4rem 4rem;}'}</style>
        <h1 className="cover-heading ">GITHUB RESUME</h1>
        <h2>{resume.nameOwner}</h2>
        <p className="font-weight-bold mr-4" >GitHub usename: {resume.loginOwner}</p>
        <p className="font-weight-bold mr-4" >Website: <a href={resume.blogOfOwner}>{resume.blogOfOwner}</a></p>
        <p className="lead"> On GitHub as an early adopter since {new Date(resume.dateOfCreation).getFullYear()}, {resume.nameOwner} is a developer with {resume.numOfRepos} public repositories and {resume.followers} followers. </p>
        <h4>Languages:</h4>
        <ListGroup horizontal className='d-flex flex-nowrap'>

        </ListGroup>

        <ListGroup as="ol" numbered>
          {languagesCards}
        </ListGroup>

        <h4>Repositories: </h4>
        {(resume.repoDetails && Object.values(resume.repoDetails).map(repo => {
          return (
            <Card key={repo.repoId}>
              <Card.Header>{repo.repoName}</Card.Header>
              <ListGroup variant="flush">
                <ListGroup.Item>{repo.repoDescription}</ListGroup.Item>
                <ListGroup.Item><Card.Link href={repo.repoSvnUrl}>{repo.repoSvnUrl}</Card.Link></ListGroup.Item>
                <ListGroup.Item>{repo.repoLanguage}</ListGroup.Item>
              </ListGroup>
            </Card>);
        }))}
      </main>
    )
  )
}

export default Home;