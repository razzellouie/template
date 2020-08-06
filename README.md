# TEMPLATE

ENVIRONMENT VARIABLES:

export SMTP_HOST=smtp.gmail.com
export SMTP_PORT=587
export SMTP_PASSWORD='Password@123'
export SMTP_USERNAME='mail.spring.template@gmail.com'
export RDS_TEMPLATE_HOST_NAME=localhost
export RDS_TEMPLATE_PORT=3306
export RDS_TEMPLATE_DB_NAME=template_api
export RDS_TEMPLATE_USERNAME=root
export RDS_TEMPLATE_PASSWORD=12345678
export API_URL=127.0.0.1:5000 
export TEMPLATE_S3_END_POINT_URL=
export TEMPLATE_S3_ACCESS_KEY=
export TEMPLATE_S3_SECRET_KEY=
export TEMPLATE_S3_BUCKET_NAME=
export FTP_SERVER=
export FTP_USERNAME=
export FTP_PORT=
export FTP_PASSWORD=
export FTP_URL=

mvn clean install
mvn spring-boot:run
