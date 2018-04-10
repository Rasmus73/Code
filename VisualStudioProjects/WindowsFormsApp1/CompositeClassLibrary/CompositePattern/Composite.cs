using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    internal class Composite : IComponent, IEnumerable<IComponent>
    {
        public string Name { get; set; }

        private List<IComponent> _children = new List<IComponent>();

        public Composite(string name)
        {
            Name = name;
        }

        #region Composite

        public void SortComponents()
        {
            _children.Sort((a, b) => { return (a.GetType().Name.CompareTo(b.GetType().Name)); });
        }

        #endregion

        #region IComponent

        public void Insert(IComponent component, int index = -1)
        {
            if(index == -1)
            {
                _children.Add(component);
            }
            else
            {
                _children.Insert(index, component);
            }
        }

        public void Remove(IComponent component)
        {
            _children.Remove(component);
        }

        public IComponent GetChild(int index)
        {
            return _children[index];
        }

        public void Draw(Graphics graphics)
        {

            System.Drawing.Pen myPen;
            myPen = new System.Drawing.Pen(System.Drawing.Color.Black);

            //graphics.DrawLine(myPen, 1, ,, l.EndX, y);
            System.Drawing.Font font = new Font("TimesNewRoman", 12);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 1);

            foreach (IComponent component in _children)
            {
                component.Draw(graphics);
            }
        }

        //public override void Display(int depth)
        //{
        //    Console.WriteLine(new String('-', depth) + name);

        //    // Recursively display child nodes

        //    foreach (Component component in _children)
        //    {
        //        component.Display(depth + 2);
        //    }
        //}

        #endregion


        #region IEnumerable
        public IEnumerator<IComponent> GetEnumerator()
        {
            foreach (IComponent child in _children)
            {
                yield return child;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion
    }
}