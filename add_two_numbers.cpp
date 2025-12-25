#include <iostream>
using namespace std;

// Definition for singly-linked list node
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        // Dummy node to simplify adding new nodes
        ListNode* dummy = new ListNode(0);
        ListNode* current = dummy; // Pointer to build the result list
        int carry = 0;             // Store carry for addition

        // Traverse both lists until all digits and carry are processed
        while (l1 != nullptr || l2 != nullptr || carry != 0) {
            int x = (l1 != nullptr) ? l1->val : 0; // get value from l1, 0 if NULL
            int y = (l2 != nullptr) ? l2->val : 0; // get value from l2, 0 if NULL
            int sum = x + y + carry;               // sum current digits + carry

            carry = sum / 10;                      // new carry for next iteration
            current->next = new ListNode(sum % 10); // create node for current digit

            current = current->next;               // move current pointer forward
            if (l1) l1 = l1->next;                // move l1 forward if exists
            if (l2) l2 = l2->next;                // move l2 forward if exists
        }

        return dummy->next; // return head of resulting sum list
    }
};

// Helper function to print linked list
void printList(ListNode* head) {
    while (head != nullptr) {
        cout << head->val;
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

// Example usage
int main() {
    // Create first number: 342 as linked list [2,4,3]
    ListNode* l1 = new ListNode(2, new ListNode(4, new ListNode(3)));

    // Create second number: 465 as linked list [5,6,4]
    ListNode* l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

    Solution s;
    ListNode* result = s.addTwoNumbers(l1, l2);

    // Print the result: should be [7,0,8]
    printList(result);
}
