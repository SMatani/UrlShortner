Create database url_shorterdb;

use url_shorterdb;

Create table UrlData (
    urlId int NOT NULL AUTO_INCREMENT,
    originalUrl varchar(2000),
    shortenUrl varchar(2000),
	PRIMARY KEY (urlId)
);
