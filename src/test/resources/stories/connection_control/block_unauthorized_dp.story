vSCG can only support a reasonble amount of authorized vDP

Narrative:
In order to keep the performance of vSCG and vDP as good as SCG 
As a vSCG administrator
I want to constraint the amount of authorized vDP to a certain number.
					 
Scenario:  Multiple vSCG nodes support at most #vSCG * 4 authorized vDP
Given I have following vSCG nodes:
|   bladeId    |   #connected_dp   |
|   abcd         |               4                |
|   eeee          |              4                 |
|   ffff             |              4                 |           
When 1 vDP named extraDp connects to the vSCG node abcd
Then the extraDp should be rejected

Scenario:  One vSCG node can support four authorized vDP
Given 4 vDPs have connected to 1 vSCG node abcde
When 1 vDP named fifthDp connects to the vSCG node abcde
Then the fifthDp should be rejected

Scenario: One vSCG node can support at most four authorized vDP
Given <amountOfDp> vDPs have connected to 1 vSCG node abcde
When 1 vDP named newDp connects to the vSCG node abcde
Then the newDp should be <rejectedOrPass>

Examples:
|   amountOfDp  |   rejectedOrPass  |
|               1           |           pass            |
|               2           |           pass            |
|               3           |           pass            |
|               4           |       rejected          |               