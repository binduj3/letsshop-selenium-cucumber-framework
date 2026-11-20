Password is set via Run-EditConfigurations-Enviornment Variables (this works only for test runner)
Set the env variable via powershell ( Both commands must run in the same window, since the variable disappears when the window closes.)
$env:LETSSHOP_PASSWORD="YourPassword"
mvn clean test
