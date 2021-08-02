# Some variables
export CLUSTER_NAME=rhdg
export PCKS12_PASSWORD=changeit
export PCKS12_ALIAS=rhdg

# Get both tls secrets in .pem format
oc get secret ${CLUSTER_NAME}-cert-secret -o jsonpath='{.data.tls\.key}' | base64 --decode > server.pem
oc get secret ${CLUSTER_NAME}-cert-secret -o jsonpath='{.data.tls\.crt}' | base64 --decode >> server.pem

# Create the PKCS file
openssl pkcs12 -export -passout env:PCKS12_PASSWORD -inkey tls.key -in tls.crt -name $PCKS12_ALIAS -out keystore.pkcs12
# Check that it worked
openssl pkcs12 -nokeys -info -in keystore.pkcs12 -passin pass:${PCKS12_PASSWORD}

# Convert to JKS
keytool -importkeystore -srckeystore keystore.pkcs12 -srcstoretype PKCS12 -destkeystore truststore.jks -deststoretype JKS -srcstorepass ${PCKS12_PASSWORD} -storepass ${PCKS12_PASSWORD}
# Create secret with the .jks file
oc create secret generic rhdg-client-truststore-secret --from-file=truststore.jks