# ImagInLexis
ImagInLexis is a tool that aims to enhance therapies programs of Logotherapeutic intervention. With the help of technology this tool was designed, which gives comfort to the therapist, as well as in the treated, complement and enrich dynamically and interactively the intervention therapies. At the same time, it aims indirectly for other purposes. It creates conditions for vocabulary enrichment, pulse recognition, the enhancement of basic knowledge and abstraction ability. Finally, it requires a combination of skills to enhance logical thinking. IMAGinLEXIS is addressed to speech therapists, special pedagogues and other professionals working with preschool and school age children, as well as adults which have difficulties in the areas mentioned above.

## Compile
To compile and deploy the project run
```
mvn clean compile jar:jar install -f pom.xml
```


<!--

## Prerequisites

* Compiler: [mpicxx](https://www.mpich.org/static/docs/v3.1/www1/mpicxx.html)
* Build tool: [GNU Make](https://www.gnu.org/software/make/)

## Building & Running

### Frontend

To build the both projects and run the executable in the frontend execute the following script:

```
./run.sh
```

### Backend

To build the both projects and submit the executable in the backend execute the following script:

```
./run_backend.sh
```

## Testing

To test the simulation's behaviour run:

```
./test.sh
```

This script will run a number of behavioural scenarios, these are:

1. Scenario 1: If there are not infected squirrels no one dies and the population is constantly increasing
2. Scenario 2: If there are not infected squirrels and the the number of actors is close to 200 then the simulation will probably terminate before 24 months
3. Scenario 3: If all squirrels are infected the simulation will terminate in early stage
4. Scenario 4: running on 1 process will terminate
5. Scenario 5: running for 48 months

## Output

The output of the simulation is displayed in 5 files:

1. Standard Output: it contains information about the initialisation and finalisation of the simulation and during each months prints the number of alive and infected squirrels
2. data/population_influx.tsv: contains for each simulated month of the simulation the population influx for each cell
3. data/infection_level.tsv: contains for each simulated month of the simulation the infection level for each cell
4. data/alive_squirrels.tsv: contains for each simulated month of the number of alive squirrels
5. data/infected_squirrels.tsv: contains for each simulated month of the number of infected squirrels

To visualise the data run:

```
python plot.py
```

This command will create four eps files in data/ folder. These figures represent:

1. Population influx for each cell across all months
2. Infection level for each cell across all months
3. Number of alive squirrels for each month
4. Number of infected squirrels for each month

## Documentation

C++ header files contain in line documentation
We used [DoxyGen](https://github.com/doxygen/doxygen)
To auto-generate a website-documentation run

```
./documentation.sh
```
You can check by opening with your browser the documentation/index.html file the project directory

## Version Control

We use [GitHub](http://github.com/) for Version Control. For the versions available, see the [tags on this repository](https://github.com/nikosxenakis/pdp_coursework).
Please send request to the owner for further investigation.

## Acknowledgements

* Thanks to anyone whose code was used, and to EPCC

# Project Title

One Paragraph of project description goes here

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

-->
## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
